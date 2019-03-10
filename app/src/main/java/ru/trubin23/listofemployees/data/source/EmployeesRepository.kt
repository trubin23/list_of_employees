package ru.trubin23.listofemployees.data.source

import androidx.paging.DataSource
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalDataSource
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteDataSource

class EmployeesRepository private constructor(
    private val employeesRemoteDataSource: EmployeesRemoteDataSource,
    private val employeesLocalDataSource: EmployeesLocalDataSource
) : EmployeesDataSource {

    override fun getEmployees(): Single<DataSource.Factory<Int, Employee>> {
        return employeesRemoteDataSource
            .getEmployees()
            .doOnSubscribe { employeesLocalDataSource.deleteEmployees() }
            .map { employeeList -> employeesLocalDataSource.insertEmployees(employeeList) }
            .toList()
            .map { employeesLocalDataSource.getEmployees() }
    }

    override fun getEmployeeById(employeeId: String): Single<Employee> {
        return employeesLocalDataSource.getEmployeeById(employeeId)
    }

    companion object {

        private var INSTANCE: EmployeesRepository? = null

        @JvmStatic
        fun getInstance(
            tasksRemoteDataSource: EmployeesRemoteDataSource,
            tasksLocalDataSource: EmployeesLocalDataSource
        ): EmployeesRepository {
            return INSTANCE ?: EmployeesRepository(
                tasksRemoteDataSource,
                tasksLocalDataSource
            ).apply { INSTANCE = this }
        }
    }
}