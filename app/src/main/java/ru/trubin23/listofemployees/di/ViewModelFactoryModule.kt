package ru.trubin23.listofemployees.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailViewModel
import ru.trubin23.listofemployees.employees.EmployeesViewModel
import javax.inject.Singleton

@Module
interface ViewModelFactoryModule {

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    @Binds
    @Singleton
    fun bindEmployeesViewModel(viewModel: EmployeesViewModel) : ViewModel

    @IntoMap
    @ViewModelKey(EmployeeDetailViewModel::class)
    @Binds
    @Singleton
    fun bindEmployeeDetailViewModel(viewModel: EmployeeDetailViewModel) : ViewModel
}