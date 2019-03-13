package ru.trubin23.listofemployees.data.source.remote

import io.reactivex.Observable
import ru.trubin23.listofemployees.data.Employee

class EmployeesRemoteRepository private constructor(
) : EmployeesRemoteDataSource {

    override fun getEmployees(): Observable<List<Employee>> {
        return Observable.concat<List<Employee>>(
            Observable.empty()
            //RetrofitClient.getEmployees("generated-01"),
            //RetrofitClient.getEmployees("generated-02"),
            //RetrofitClient.getEmployees("generated-03")
        )
    }

    companion object {
        private var INSTANCE: EmployeesRemoteRepository? = null

        @JvmStatic
        fun getInstance(): EmployeesRemoteRepository {
            if (INSTANCE == null) {
                synchronized(EmployeesRemoteRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = EmployeesRemoteRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}