package ru.trubin23.listofemployees.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.trubin23.listofemployees.EmployeesApplication
import ru.trubin23.listofemployees.di.datasource.EmployeesDataSourceModule
import ru.trubin23.listofemployees.di.injectors.ActivityBindingModule
import ru.trubin23.listofemployees.di.injectors.FragmentBindingModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class, FragmentBindingModule::class,
        EmployeesDataSourceModule::class, ContextModule::class]
)
interface AppComponent : AndroidInjector<EmployeesApplication>