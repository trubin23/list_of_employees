package ru.trubin23.listofemployees.employees

import androidx.paging.DataSource
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeesSourceFactory(private val employeesRepository: EmployeesRepository) : DataSource.Factory<Int, Employee>() {

    override fun create(): DataSource<Int, Employee> {
        return EmployeeDataSource(employeesRepository)
    }
}