package com.namhyun.seoul2018.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.namhyun.seoul2018.data.Course
import com.namhyun.seoul2018.data.Place
import com.namhyun.seoul2018.data.PlaceDetail
import com.namhyun.seoul2018.utilities.DATABASE_NAME

@Database(entities = [Place::class, PlaceDetail::class, Course::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placesDao(): PlacesDao

    abstract fun placeDetailDao(): PlaceDetailDao

    abstract fun courseDao(): CourseDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}