package ru.trubin23.listofemployees.data.source.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.trubin23.listofemployees.data.Employee

interface RemoteService {

    @GET("{jsonName}.json?raw=true")
    fun getEmployees(@Path("jsonName") jsonName: String): Observable<List<Employee>>
}