package ru.trubin23.listofemployees.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EducationPeriod(

    @SerializedName("start")
    @Expose
    var start: String,

    @SerializedName("end")
    @Expose
    var end: String

) {
    override fun toString(): String {
        return "${updateDateString(start)} - ${updateDateString(end)}"
    }

    private fun updateDateString(time: String): String {
        try {
            val date = inputDateFormat.parse(time)
            return outputDateFormat.format(date)
        } catch (ignore: ParseException) {
        }
        return ""
    }

    companion object {
        private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
        private val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }
}