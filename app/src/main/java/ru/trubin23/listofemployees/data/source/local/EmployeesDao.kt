package ru.trubin23.listofemployees.data.source.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
import ru.trubin23.listofemployees.data.Employee

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun getEmployees(): DataSource.Factory<Int, Employee>

    @Query("SELECT * FROM employees WHERE id = :employeeId")
    fun getEmployeeById(employeeId: String): Maybe<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(tasks: List<Employee>)

    @Query("DELETE FROM employees")
    fun deleteEmployees()
}