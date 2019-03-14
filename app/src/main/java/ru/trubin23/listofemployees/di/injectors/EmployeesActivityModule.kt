package ru.trubin23.listofemployees.di.injectors

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.trubin23.listofemployees.employees.EmployeesActivity

@Module
interface EmployeesActivityModule {

    @ContributesAndroidInjector
    fun inject(): EmployeesActivity

}