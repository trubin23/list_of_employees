package ru.trubin23.listofemployees.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailViewModel
import ru.trubin23.listofemployees.employees.EmployeesViewModel

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    @Binds
    fun bindEmployeesViewModel(viewModel: EmployeesViewModel) : ViewModel

    @IntoMap
    @ViewModelKey(EmployeeDetailViewModel::class)
    @Binds
    fun bindEmployeeDetailViewModel(viewModel: EmployeeDetailViewModel) : ViewModel
}