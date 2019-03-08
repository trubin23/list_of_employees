package ru.trubin23.listofemployees.data

import com.google.gson.annotations.SerializedName

enum class Temperament(private val code: Int) {

    @SerializedName("melancholic")
    MELANCHOLIC(0),
    @SerializedName("phlegmatic")
    PHLEGMATIC(1),
    @SerializedName("sanguine")
    SANGUINE(2),
    @SerializedName("choleric")
    CHOLERIC(3);

    fun getCode(): Int = code
}