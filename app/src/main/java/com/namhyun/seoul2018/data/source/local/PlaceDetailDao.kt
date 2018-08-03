package com.namhyun.seoul2018.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namhyun.seoul2018.data.PlaceDetail

@Dao
interface PlaceDetailDao {
    @Query("SELECT * FROM places_detail WHERE id = :id")
    fun getPlaceDetail(id: Int): LiveData<PlaceDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(placeDetail: PlaceDetail)
}