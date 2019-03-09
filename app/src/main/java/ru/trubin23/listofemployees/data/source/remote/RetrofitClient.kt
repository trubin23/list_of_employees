package ru.trubin23.listofemployees.data.source.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.trubin23.listofemployees.data.Employee

object RetrofitClient {

    private val BASE_URL = "https://github.com/SkbkonturMobile/mobile-test-droid/blob/master/json/"

    private var sRemoteService: RemoteService? = null

    private fun getRemoteService(): RemoteService? {
        if (sRemoteService == null) {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            sRemoteService = retrofit.create(RemoteService::class.java)
        }
        return sRemoteService
    }

    fun getEmployees(jsonName: String): Observable<List<Employee>> {
        return getRemoteService()!!.getEmployees(jsonName)
    }
}