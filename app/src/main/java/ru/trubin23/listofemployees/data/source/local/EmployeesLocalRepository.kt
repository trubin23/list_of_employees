package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import io.reactivex.Maybe
import ru.trubin23.listofemployees.data.Employee
import javax.inject.Inject

class EmployeesLocalRepository @Inject constructor(
) : EmployeesLocalDataSource {

    lateinit var employeesDao: EmployeesDao

    override fun getEmployees(searchLine: String): DataSource.Factory<Int, Employee> {
        val phoneNumberTemperament = if (searchLine.contains(Regex("([^0-9])"))) {
            ""
        } else {
            searchLine
                .replace(Regex("([^0-9])"), "")
                .replace(Regex("([0-9])"), { result -> "${result.value}%" })

        }
        return employeesDao.getEmployees(searchLine, phoneNumberTemperament)
    }

    override fun getEmployeeById(employeeId: String): Maybe<Employee> = employeesDao.getEmployeeById(employeeId)

    override fun insertEmployees(employees: List<Employee>) = employeesDao.insertEmployees(employees)

    override fun deleteEmployees() = employeesDao.deleteEmployees()
}