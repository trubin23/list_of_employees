package ru.trubin23.listofemployees.data.source.preferences

interface EmployeesPreferencesDataSource {

    fun saveUpdateTime(timestamp: Long)

    fun loadUpdateTime(): Long

    fun resetUpdateTime()

}