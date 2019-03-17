package ru.trubin23.listofemployees.di.datasource.preferences

import dagger.Binds
import dagger.Module
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesDataSource
import ru.trubin23.listofemployees.data.source.preferences.EmployeesPreferencesRepository
import javax.inject.Singleton

@Module(includes = [SharedPreferencesModule::class])
interface PreferencesDataSourceModule {

    @Binds
    fun getDataSource(impl: EmployeesPreferencesRepository): EmployeesPreferencesDataSource
}