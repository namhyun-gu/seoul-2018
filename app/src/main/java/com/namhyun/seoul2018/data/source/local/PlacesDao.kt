package com.namhyun.seoul2018.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namhyun.seoul2018.data.Place

@Dao
interface PlacesDao {
    @Query("SELECT * FROM places ORDER BY id DESC")
    fun getPlaces(): LiveData<List<Place>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(notices: List<Place>)
}