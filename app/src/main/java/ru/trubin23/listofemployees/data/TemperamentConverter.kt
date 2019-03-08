package ru.trubin23.listofemployees.data

import androidx.room.TypeConverter

class TemperamentConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun getTemperament(code: Int?): Temperament? {
            for (temperament in Temperament.values()) {
                if (temperament.getCode() == code) {
                    return temperament
                }
            }
            return null
        }

        @TypeConverter
        @JvmStatic
        fun getTemperamentInt(temperament: Temperament?): Int? = temperament?.getCode()
    }
}