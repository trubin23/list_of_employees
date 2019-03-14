package ru.trubin23.listofemployees

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.trubin23.listofemployees.di.DaggerAppComponent
import ru.trubin23.listofemployees.di.datasource.DaggerEmployeesDataSourceComponent

class EmployeesApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .employeesDataSourceComponent(DaggerEmployeesDataSourceComponent.builder().build())
            .build()
    }
}