package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee

class EmployeesLocalRepository private constructor(
    private val employeesDao: EmployeesDao
) : EmployeesLocalDataSource {

    override fun getEmployees(): DataSource.Factory<Int, Employee> = employeesDao.getEmployees()

    override fun getEmployeeById(employeeId: String): Maybe<Employee> = employeesDao.getEmployeeById(employeeId)

    override fun insertEmployees(employees: List<Employee>) = employeesDao.insertEmployees(employees)

    override fun deleteEmployees() = employeesDao.deleteEmployees()

    companion object {
        private var INSTANCE: EmployeesLocalRepository? = null

        @JvmStatic
        fun getInstance(employeesDao: EmployeesDao): EmployeesLocalRepository {
            if (INSTANCE == null) {
                synchronized(EmployeesLocalRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = EmployeesLocalRepository(employeesDao)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}