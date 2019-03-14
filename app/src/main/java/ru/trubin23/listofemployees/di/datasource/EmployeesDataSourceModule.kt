package ru.trubin23.listofemployees.di.datasource

import dagger.Binds
import dagger.Module
import ru.trubin23.listofemployees.data.source.EmployeesDataSource
import ru.trubin23.listofemployees.data.source.EmployeesRepository
import ru.trubin23.listofemployees.di.datasource.local.LocalDataSourceModule
import ru.trubin23.listofemployees.di.datasource.preferences.PreferencesDataSourceModule
import ru.trubin23.listofemployees.di.datasource.remote.RemoteDataSourceModule
import javax.inject.Singleton

@Module(includes = [LocalDataSourceModule::class, RemoteDataSourceModule::class, PreferencesDataSourceModule::class])
interface EmployeesDataSourceModule {

    @Binds
    fun getDataSource(impl: EmployeesRepository): EmployeesDataSource
}