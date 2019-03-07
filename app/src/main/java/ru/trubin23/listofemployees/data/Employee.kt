package ru.trubin23.listofemployees.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employees")
data class Employee @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    @Expose
    var height: Double? = null,

    @ColumnInfo(name = "biography")
    @SerializedName("biography")
    @Expose
    var biography: String? = null,

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    @Expose
    var temperament: String? = null,

    @Embedded(prefix = "education")
    @SerializedName("educationPeriod")
    @Expose
    var educationPeriod: EducationPeriod? = null
)