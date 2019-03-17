package ru.trubin23.listofemployees.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.trubin23.listofemployees.data.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeesDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeesDao
}