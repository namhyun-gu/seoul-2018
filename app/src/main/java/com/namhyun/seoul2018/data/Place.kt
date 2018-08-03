package com.namhyun.seoul2018.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "places")
data class Place(
    @PrimaryKey @SerializedName("idx") val id: Int,
    @SerializedName("name") var title: String,
    @SerializedName("title") val desc: String,
    val address: String,
    val lat: Double,
    @SerializedName("lng") val lon: Double,
    val type: Int,
    @SerializedName("img_pc") val imageUrl: String
)