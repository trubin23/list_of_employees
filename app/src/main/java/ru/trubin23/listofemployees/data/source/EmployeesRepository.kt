package ru.trubin23.listofemployees.data.source

import androidx.paging.DataSource
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalDataSource
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesDataSource
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteDataSource
import java.util.*

class EmployeesRepository private constructor(
    private val employeesRemoteDataSource: EmployeesRemoteDataSource,
    private val employeesLocalDataSource: EmployeesLocalDataSource,
    private val employeesPreferencesRepository: EmployeesPreferencesDataSource
) : EmployeesDataSource {

    private var firstRequest: Boolean = true

    override fun getEmployees(forceUpdate: Boolean): Single<DataSource.Factory<Int, Employee>> {
        val updateTime = employeesPreferencesRepository.loadUpdateTime()
        return if ((updateTime + ONE_MINUTE < Date().time && firstRequest) || forceUpdate) {
            firstRequest = false
            getEmployeesFromNetwork()
                .doOnSuccess { employeesPreferencesRepository.saveUpdateTime(Date().time) }
        } else {
            firstRequest = false
            getEmployeesFromLocalStorage()
        }
    }

    private fun getEmployeesFromLocalStorage(): Single<DataSource.Factory<Int, Employee>> {
        return Single.fromCallable { employeesLocalDataSource.getEmployees() }
    }

    private fun getEmployeesFromNetwork(): Single<DataSource.Factory<Int, Employee>> {
        return employeesRemoteDataSource
            .getEmployees()
            .doOnSubscribe { employeesLocalDataSource.deleteEmployees() }
            .map { employeeList -> employeesLocalDataSource.insertEmployees(employeeList) }
            .toList()
            .map { employeesLocalDataSource.getEmployees() }
    }

    override fun getEmployeeById(employeeId: String): Maybe<Employee> {
        return employeesLocalDataSource.getEmployeeById(employeeId)
    }

    companion object {

        private const val ONE_MINUTE = 60_000

        private var INSTANCE: EmployeesRepository? = null

        @JvmStatic
        fun getInstance(
            employeesRemoteDataSource: EmployeesRemoteDataSource,
            employeesLocalDataSource: EmployeesLocalDataSource,
            employeesPreferencesRepository: EmployeesPreferencesDataSource
        ): EmployeesRepository {
            return INSTANCE ?: EmployeesRepository(
                employeesRemoteDataSource,
                employeesLocalDataSource,
                employeesPreferencesRepository
            ).apply { INSTANCE = this }
        }
    }
}