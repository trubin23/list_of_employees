package ru.trubin23.listofemployees.data.source

import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalDataSource
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteDataSource

class EmployeesRepository private constructor(
    private val employeesRemoteDataSource: EmployeesRemoteDataSource,
    private val employeesLocalDataSource: EmployeesLocalDataSource
) : EmployeesDataSource {

    override fun getEmployees(): Single<List<Employee>> {
        return Single.fromCallable {
            employeesLocalDataSource.deleteEmployees()

            employeesRemoteDataSource
                .getEmployees()
                .subscribe { employeeList ->
                    run {
                        employeesLocalDataSource.insertEmployees(employeeList)
                    }
                }

            employeesLocalDataSource.getEmployees()
        }
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