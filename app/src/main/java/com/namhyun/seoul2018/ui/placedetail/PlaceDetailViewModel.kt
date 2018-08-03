package com.namhyun.seoul2018.ui.placedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.namhyun.seoul2018.data.PlaceDetail
import com.namhyun.seoul2018.data.source.local.AppDatabase
import com.namhyun.seoul2018.utilities.KEY_PLACE_ID
import com.namhyun.seoul2018.workers.RequestPlaceDetailWorker

class PlaceDetailViewModel(db: AppDatabase, private val placeId: Int) : ViewModel() {
    var placeDetail: LiveData<PlaceDetail> = db.placeDetailDao().getPlaceDetail(placeId)

    fun requestPlaceDetail() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putInt(KEY_PLACE_ID, placeId)
            .build()

        val request = OneTimeWorkRequestBuilder<RequestPlaceDetailWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val workManager = WorkManager.getInstance()
        workManager.enqueue(request)
    }
}