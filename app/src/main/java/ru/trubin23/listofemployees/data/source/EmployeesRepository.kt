package ru.trubin23.listofemployees.data.source

import androidx.paging.DataSource
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalDataSource
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesDataSource
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteDataSource
import java.util.*
import javax.inject.Inject

class EmployeesRepository @Inject constructor(
    private val employeesRemoteDataSource: EmployeesRemoteDataSource,
    private val employeesLocalDataSource: EmployeesLocalDataSource,
    private val employeesPreferencesRepository: EmployeesPreferencesDataSource
) : EmployeesDataSource {

    private var firstRequest: Boolean = true

    override fun getEmployees(forceUpdate: Boolean, searchLine: String): Single<DataSource.Factory<Int, Employee>> {
        val updateTime = employeesPreferencesRepository.loadUpdateTime()
        return if ((updateTime + ONE_MINUTE < Date().time && firstRequest) || forceUpdate) {
            firstRequest = false
            getEmployeesFromNetwork(searchLine)
                .doOnSuccess { employeesPreferencesRepository.saveUpdateTime(Date().time) }
        } else {
            firstRequest = false
            getEmployeesFromLocalStorage(searchLine)
        }
    }

    private fun getEmployeesFromLocalStorage(searchLine: String): Single<DataSource.Factory<Int, Employee>> {
        return Single.fromCallable { employeesLocalDataSource.getEmployees(searchLine) }
    }

    private fun getEmployeesFromNetwork(searchLine: String): Single<DataSource.Factory<Int, Employee>> {
        return employeesRemoteDataSource
            .getEmployees()
            .doOnSubscribe { employeesLocalDataSource.deleteEmployees() }
            .map { employeeList -> employeesLocalDataSource.insertEmployees(employeeList) }
            .toList()
            .map { employeesLocalDataSource.getEmployees(searchLine) }
    }

    override fun getEmployeeById(employeeId: String): Maybe<Employee> {
        return employeesLocalDataSource.getEmployeeById(employeeId)
    }

    companion object {

        private const val ONE_MINUTE = 60_000
    }
}