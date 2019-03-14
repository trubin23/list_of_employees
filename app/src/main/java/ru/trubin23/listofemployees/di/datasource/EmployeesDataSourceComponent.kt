package ru.trubin23.listofemployees.di.datasource

import dagger.Component
import ru.trubin23.listofemployees.data.source.EmployeesDataSource
import javax.inject.Singleton

@Component(modules = [EmployeesDataSourceModule::class])
interface EmployeesDataSourceComponent {

    val dataSource: EmployeesDataSource
}