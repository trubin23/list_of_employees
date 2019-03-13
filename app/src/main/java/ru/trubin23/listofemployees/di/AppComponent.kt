package ru.trubin23.listofemployees.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.trubin23.listofemployees.EmployeesApplication
import ru.trubin23.listofemployees.di.injectors.EmployeesFragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    EmployeesFragmentModule::class, RetrofitClientModule::class])
interface AppComponent : AndroidInjector<EmployeesApplication> {

}