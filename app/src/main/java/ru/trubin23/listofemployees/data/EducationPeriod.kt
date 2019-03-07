package ru.trubin23.listofemployees.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EducationPeriod {

    @SerializedName("start")
    @Expose
    var start: String? = null

    @SerializedName("end")
    @Expose
    var end: String? = null

}