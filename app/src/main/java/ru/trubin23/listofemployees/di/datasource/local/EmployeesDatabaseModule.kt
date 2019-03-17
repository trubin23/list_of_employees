package ru.trubin23.listofemployees.di.datasource.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.trubin23.listofemployees.data.source.local.EmployeesDao
import ru.trubin23.listofemployees.data.source.local.EmployeesDatabase
import ru.trubin23.listofemployees.di.ContextModule

@Module(includes = [ContextModule::class])
class EmployeesDatabaseModule {

    @Provides
    fun provideSharedPreferences(context: Context): EmployeesDatabase {
        val databaseName = "Employees.db"
        return Room.databaseBuilder(
            context.applicationContext,
            EmployeesDatabase::class.java, databaseName
        )
            .build()
    }

    @Provides
    fun provideEmployeesDao(database: EmployeesDatabase): EmployeesDao {
        return database.employeesDao()
    }
}