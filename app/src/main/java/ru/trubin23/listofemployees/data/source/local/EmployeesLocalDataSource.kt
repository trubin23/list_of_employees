package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee

interface EmployeesLocalDataSource {

    fun getEmployees(): DataSource.Factory<Int, Employee>

    fun getEmployeeById(employeeId: String): Maybe<Employee>

    fun insertEmployees(employees: List<Employee>)

    fun deleteEmployees()
}