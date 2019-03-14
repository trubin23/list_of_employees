package ru.trubin23.listofemployees.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.trubin23.listofemployees.EmployeesApplication
import ru.trubin23.listofemployees.di.datasource.EmployeesDataSourceComponent
import ru.trubin23.listofemployees.di.injectors.EmployeesActivityModule
import ru.trubin23.listofemployees.di.injectors.EmployeesFragmentModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class,
        EmployeesActivityModule::class, EmployeesFragmentModule::class],
    dependencies = [EmployeesDataSourceComponent::class]
)
interface AppComponent : AndroidInjector<EmployeesApplication>