package com.namhyun.seoul2018.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "courses",
    indices = [Index("placeId")],
    foreignKeys = [ForeignKey(
        entity = PlaceDetail::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("placeId"),
        onDelete = CASCADE
    )]
)
data class Course(
    @PrimaryKey val id: Int,
    val placeId: Int,
    val imageUrl: String,
    val title: String,
    val desc: String
)