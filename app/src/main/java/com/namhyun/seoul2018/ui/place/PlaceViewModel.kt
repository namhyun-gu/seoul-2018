package com.namhyun.seoul2018.ui.place

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.namhyun.seoul2018.data.Place
import com.namhyun.seoul2018.data.source.local.AppDatabase
import com.namhyun.seoul2018.workers.RequestPlacesWorker

class PlaceViewModel(application: Application) : AndroidViewModel(application) {
    private var db: AppDatabase = AppDatabase.getInstance(application)
    var places: LiveData<List<Place>>

    init {
        places = db.placesDao().getPlaces()
    }

    fun requestPlaces() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<RequestPlacesWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance()
        workManager.enqueue(request)
    }
}