package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import io.reactivex.Maybe
import ru.trubin23.listofemployees.data.Employee
import javax.inject.Inject

class EmployeesLocalRepository @Inject constructor(
    private var employeesDao: EmployeesDao
) : EmployeesLocalDataSource {

    override fun getEmployees(searchLine: String): DataSource.Factory<Int, Employee> {
        val phoneNumberDigits = if (searchLine.replace(Regex("\\s"),"").contains(Regex("\\D"))) {
            ""
        } else {
            searchLine
                .replace(Regex("\\s"),"")
        }
        return employeesDao.getEmployees(searchLine, phoneNumberDigits)
    }

    override fun getEmployeeById(employeeId: String): Maybe<Employee> = employeesDao.getEmployeeById(employeeId)

    override fun insertEmployees(employees: List<Employee>) = employeesDao.insertEmployees(employees)

    override fun deleteEmployees() = employeesDao.deleteEmployees()
}