package com.namhyun.seoul2018.workers

import android.util.Log
import androidx.work.Worker
import com.namhyun.seoul2018.data.source.local.AppDatabase
import com.namhyun.seoul2018.data.source.remote.ServiceGenerator

class RequestPlacesWorker : Worker() {
    override fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val service = ServiceGenerator.generate()

        return try {
            val response = service.getAllPlace().execute()
            val places = response.body()?.data!!
            places.forEach {
                it.title = it.title.replace("<em>", "").replace("</em>", "")
            }
            db.placesDao().insertAll(places)
            Log.d(TAG, "Success get places")
            Worker.Result.SUCCESS
        } catch (e: Exception) {
            Log.e(TAG, "Can't get places", e)
            Worker.Result.FAILURE
        }
    }

    companion object {

        val TAG = RequestPlacesWorker::class.java.simpleName
    }
}