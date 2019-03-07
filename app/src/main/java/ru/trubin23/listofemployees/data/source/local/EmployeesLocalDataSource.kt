package ru.trubin23.listofemployees.data.source.local

import ru.trubin23.listofemployees.data.Employee

interface EmployeesLocalDataSource {

    fun getEmployees(): List<Employee>

    fun insertEmployees(employees: List<Employee>)

    fun deleteEmployees()
}