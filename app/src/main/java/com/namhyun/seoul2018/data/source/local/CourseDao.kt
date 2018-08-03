package com.namhyun.seoul2018.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namhyun.seoul2018.data.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses WHERE placeId = :placeId")
    fun getCoursesByPlaceId(placeId: Int): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(courses: List<Course>)
}