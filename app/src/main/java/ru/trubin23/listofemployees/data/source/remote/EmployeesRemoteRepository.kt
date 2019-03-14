package ru.trubin23.listofemployees.data.source.remote

import io.reactivex.Observable
import ru.trubin23.listofemployees.data.Employee
import javax.inject.Inject

class EmployeesRemoteRepository @Inject constructor(
    private val remoteService: RemoteService
) : EmployeesRemoteDataSource {

    override fun getEmployees(): Observable<List<Employee>> {
        return Observable.concat<List<Employee>>(
            remoteService.getEmployees("generated-01"),
            remoteService.getEmployees("generated-02"),
            remoteService.getEmployees("generated-03")
        )
    }
}