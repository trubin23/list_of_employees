package ru.trubin23.listofemployees.data.source.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class EmployeesPreferencesRepository @Inject constructor(
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

        private const val TIMESTAMP = "TIMESTAMP"

        private const val TIMESTAMP_MINIMUM: Long = 0L
    }
}