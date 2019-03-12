package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import io.reactivex.Maybe
import ru.trubin23.listofemployees.data.Employee

interface EmployeesLocalDataSource {

    fun getEmployees(searchLine: String): DataSource.Factory<Int, Employee>

    fun getEmployeeById(employeeId: String): Maybe<Employee>

    fun insertEmployees(employees: List<Employee>)

    fun deleteEmployees()
}