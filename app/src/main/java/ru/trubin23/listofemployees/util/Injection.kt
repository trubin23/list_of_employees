package ru.trubin23.listofemployees.util

import android.content.Context
import ru.trubin23.listofemployees.data.source.EmployeesRepository
import ru.trubin23.listofemployees.data.source.local.EmployeesDatabase
import ru.trubin23.listofemployees.data.source.local.EmployeesLocalRepository
import ru.trubin23.listofemployees.data.source.remote.EmployeesRemoteRepository

object Injection {

    fun provideEmployeesRepository(context: Context): EmployeesRepository {
        val database = EmployeesDatabase.getInstance(context)

        return EmployeesRepository.getInstance(
            EmployeesRemoteRepository.getInstance(),
            EmployeesLocalRepository.getInstance(database.employeesDao()))
    }

}