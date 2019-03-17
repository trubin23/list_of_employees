package ru.trubin23.listofemployees.di.datasource

import dagger.Component
import ru.trubin23.listofemployees.data.source.EmployeesDataSource
import ru.trubin23.listofemployees.di.ContextModule

@Component(modules = [EmployeesDataSourceModule::class, ContextModule::class])
interface EmployeesDataSourceComponent {

    val dataSource: EmployeesDataSource
}