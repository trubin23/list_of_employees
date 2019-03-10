package ru.trubin23.listofemployees.data.source.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class EmployeesPreferencesRepository private constructor(
    private val preferences: SharedPreferences
) : EmployeesPreferencesDataSource {

    override fun saveUpdateTime(timestamp: Long) {
        val editor = preferences.edit()
        editor.putLong(TIMESTAMP, timestamp)
        editor.apply()
    }

    override fun loadUpdateTime(): Long = preferences.getLong(TIMESTAMP, TIMESTAMP_MINIMUM)

    override fun resetUpdateTime() {
        val editor = preferences.edit()
        editor.putLong(TIMESTAMP, TIMESTAMP_MINIMUM)
        editor.apply()
    }

    companion object {

        private const val PREFS_NAME = "EMPLOYEES_PREFERENCES"

        private const val TIMESTAMP = "TIMESTAMP"

        private const val TIMESTAMP_MINIMUM: Long = 0L

        private var INSTANCE: EmployeesPreferencesRepository? = null

        @JvmStatic
        fun getInstance(context: Context): EmployeesPreferencesRepository {
            if (INSTANCE == null) {
                synchronized(EmployeesPreferencesRepository::javaClass) {
                    if (INSTANCE == null) {
                        val preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                        INSTANCE = EmployeesPreferencesRepository(preferences)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}