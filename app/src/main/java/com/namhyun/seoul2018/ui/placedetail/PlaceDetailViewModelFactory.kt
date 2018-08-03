package com.namhyun.seoul2018.ui.placedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namhyun.seoul2018.data.source.local.AppDatabase

class PlaceDetailViewModelFactory(private val db: AppDatabase, private val placeId: Int) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceDetailViewModel(db, placeId) as T
    }
}