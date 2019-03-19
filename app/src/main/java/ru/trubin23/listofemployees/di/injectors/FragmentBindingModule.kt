package ru.trubin23.listofemployees.di.injectors

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailFragment
import ru.trubin23.listofemployees.employees.EmployeesFragment

@Module
interface FragmentBindingModule {

    @ContributesAndroidInjector
    fun getEmployeesFragment(): EmployeesFragment

    @ContributesAndroidInjector
    fun getEmployeeDetailFragment(): EmployeeDetailFragment

}