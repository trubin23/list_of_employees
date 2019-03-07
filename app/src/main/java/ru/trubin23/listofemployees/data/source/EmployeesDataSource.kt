package ru.trubin23.listofemployees.data.source

import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee

interface EmployeesDataSource {

    fun getEmployees(): Single<List<Employee>>

}