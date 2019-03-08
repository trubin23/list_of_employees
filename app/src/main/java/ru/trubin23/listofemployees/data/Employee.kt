package ru.trubin23.listofemployees.data

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employees")
@TypeConverters(TemperamentConverter::class)
data class Employee constructor(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("phone")
    @Expose
    var phone: String,

    @SerializedName("height")
    @Expose
    var height: Float,

    @SerializedName("biography")
    @Expose
    var biography: String,

    @SerializedName("temperament")
    @Expose
    var temperament: Temperament,

    @Embedded(prefix = "education")
    @SerializedName("educationPeriod")
    @Expose
    var educationPeriod: EducationPeriod
)