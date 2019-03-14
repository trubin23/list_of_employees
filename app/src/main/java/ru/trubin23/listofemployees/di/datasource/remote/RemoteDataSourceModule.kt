package ru.trubin23.listofemployees.di.datasource.remote

import dagger.Binds
import dagger.Module
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteDataSource
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteRepository
import javax.inject.Singleton

@Module(includes = [RetrofitClientModule::class])
interface RemoteDataSourceModule {

    @Binds
    fun getDataSource(impl: EmployeesRemoteRepository): EmployeesRemoteDataSource
}