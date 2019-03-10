package ru.trubin23.listofemployees.data.source

import androidx.paging.DataSource
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee

interface EmployeesDataSource {

    fun getEmployees(): Single<DataSource.Factory<Int, Employee>>

    fun getEmployeeById(employeeId: String): Maybe<Employee>
}