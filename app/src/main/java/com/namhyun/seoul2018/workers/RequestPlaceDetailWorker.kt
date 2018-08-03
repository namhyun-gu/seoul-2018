package com.namhyun.seoul2018.workers

import android.util.Log
import androidx.work.Worker
import com.namhyun.seoul2018.data.source.local.AppDatabase
import com.namhyun.seoul2018.data.source.remote.CourseParser
import com.namhyun.seoul2018.data.source.remote.PlaceDetailParser
import com.namhyun.seoul2018.data.source.remote.ServiceGenerator
import com.namhyun.seoul2018.utilities.KEY_PLACE_ID
import org.jsoup.Jsoup

class RequestPlaceDetailWorker : Worker() {

    override fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val service = ServiceGenerator.generate()
        val placeId = inputData.getInt(KEY_PLACE_ID, 0)

        return try {
            val response = service.getPlaceDetail(placeId).execute()
            val document = Jsoup.parse(response.body())
            val placeDetail = PlaceDetailParser.parse(placeId, document)
            db.placeDetailDao().insert(placeDetail)
            val courses = CourseParser.parse(placeId, document)
            db.courseDao().insertAll(courses)
            Log.d(TAG, "Success get place detail")
            Worker.Result.SUCCESS
        } catch (e: Exception) {
            Log.e(RequestPlacesWorker.TAG, "Can't get place detail", e)
            Worker.Result.FAILURE
        }
    }

    companion object {

        val TAG = RequestPlacesWorker::class.java.simpleName
    }
}