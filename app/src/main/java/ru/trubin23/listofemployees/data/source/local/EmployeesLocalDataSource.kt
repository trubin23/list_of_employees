package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import ru.trubin23.listofemployees.data.Employee

interface EmployeesLocalDataSource {

    fun getEmployees(): DataSource.Factory<Int, Employee>

    fun insertEmployees(employees: List<Employee>)

    fun deleteEmployees()
}