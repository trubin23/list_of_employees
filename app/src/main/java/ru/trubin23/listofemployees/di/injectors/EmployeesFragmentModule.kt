package ru.trubin23.listofemployees.di.injectors

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.trubin23.listofemployees.employees.EmployeesActivity
import ru.trubin23.listofemployees.employees.EmployeesFragment

@Module
interface EmployeesFragmentModule {

    @ContributesAndroidInjector
    fun inject(): EmployeesFragment

}