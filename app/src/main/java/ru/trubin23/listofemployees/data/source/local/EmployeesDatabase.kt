package ru.trubin23.listofemployees.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.trubin23.listofemployees.data.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeesDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeesDao

    companion object {

        private var INSTANCE: EmployeesDatabase? = null

        private val mLock = Any()

        fun getInstance(context: Context): EmployeesDatabase {
            synchronized(mLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        EmployeesDatabase::class.java, "Employees.db")
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}