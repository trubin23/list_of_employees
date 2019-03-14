package ru.trubin23.listofemployees.di.datasource.local

import dagger.Binds
import dagger.Module
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalDataSource
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalRepository
import javax.inject.Singleton

@Module
interface LocalDataSourceModule {

    @Singleton
    @Binds
    fun getDataSource(impl: EmployeesLocalRepository): EmployeesLocalDataSource
}