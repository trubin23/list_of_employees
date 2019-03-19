package ru.trubin23.listofemployees.di.injectors

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailActivity
import ru.trubin23.listofemployees.employees.EmployeesActivity

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector
    fun getEmployeesActivity(): EmployeesActivity

    @ContributesAndroidInjector
    fun getEmployeeDetailActivity(): EmployeeDetailActivity

}