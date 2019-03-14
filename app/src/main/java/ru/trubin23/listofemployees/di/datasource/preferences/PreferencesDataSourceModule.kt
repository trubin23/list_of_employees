package ru.trubin23.listofemployees.di.datasource.preferences

import dagger.Binds
import dagger.Module
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesDataSource
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesRepository
import javax.inject.Singleton

@Module
interface PreferencesDataSourceModule {

    @Singleton
    @Binds
    fun getDataSource(impl: EmployeesPreferencesRepository): EmployeesPreferencesDataSource
}