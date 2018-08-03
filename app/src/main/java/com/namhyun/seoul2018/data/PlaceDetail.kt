package com.namhyun.seoul2018.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "places_detail", foreignKeys = [(ForeignKey(
        entity = Place::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = CASCADE
    ))]
)
data class PlaceDetail(
    @PrimaryKey val id: Int,
    val imageUrl: String,
    val title: String,
    val subTitle: String,
    val hash: String,
    val desc: String,
    val address: String,
    val findBusWay: String?,
    val findMetroWay: String?,
    val workTime: String?,
    val price: String?,
    val homeUrl: String?
)