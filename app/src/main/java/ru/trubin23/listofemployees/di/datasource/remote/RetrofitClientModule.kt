package ru.trubin23.listofemployees.di.datasource.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.trubin23.listofemployees.data.source.remote.RemoteService

@Module
class RetrofitClientModule {

    private val BASE_URL = "https://github.com/SkbkonturMobile/mobile-test-droid/blob/master/json/"

    @Provides
    fun getRemoteService(): RemoteService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(RemoteService::class.java)
    }
}