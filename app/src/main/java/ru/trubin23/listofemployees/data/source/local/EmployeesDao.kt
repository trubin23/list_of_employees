package ru.trubin23.listofemployees.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.trubin23.listofemployees.data.Employee

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employees")
    fun getEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(tasks: List<Employee>)

    @Query("DELETE FROM employees")
    fun deleteEmployees()
}