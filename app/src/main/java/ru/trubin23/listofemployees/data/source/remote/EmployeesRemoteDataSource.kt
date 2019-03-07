package ru.trubin23.listofemployees.data.source.remote

import io.reactivex.Observable
import ru.trubin23.listofemployees.data.Employee

interface EmployeesRemoteDataSource {

    fun getEmployees(): Observable<List<Employee>>
}