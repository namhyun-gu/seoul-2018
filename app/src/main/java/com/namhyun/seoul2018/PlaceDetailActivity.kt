package com.namhyun.seoul2018

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.namhyun.seoul2018.data.PlaceDetail
import com.namhyun.seoul2018.data.source.local.AppDatabase
import com.namhyun.seoul2018.ui.placedetail.PlaceDetailAdapter
import com.namhyun.seoul2018.ui.placedetail.PlaceDetailViewModel
import com.namhyun.seoul2018.ui.placedetail.PlaceDetailViewModelFactory
import com.namhyun.seoul2018.utilities.KEY_PLACE_ID
import com.namhyun.seoul2018.utilities.setImageUrl
import kotlinx.android.synthetic.main.activity_place_detail.*

class PlaceDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaceDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
        initViews()
        val db = AppDatabase.getInstance(this)
        if (intent != null && intent.hasExtra(KEY_PLACE_ID)) {
            val placeId = intent.getIntExtra(KEY_PLACE_ID, 0)
            val factory = PlaceDetailViewModelFactory(db, placeId)
            viewModel = ViewModelProviders.of(this, factory).get(PlaceDetailViewModel::class.java)
            viewModel.placeDetail.observe(this, Observer {
                if (it != null) {
                    showPlaceDetail(it)
                }
            })
            viewModel.requestPlaceDetail()
        }
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.supportTranslucentStatus(this, TYPE_PADDING)
//        shadow.supportTranslucentStatus(this, TYPE_MARGIN)

        place_detail_list.layoutManager = LinearLayoutManager(this)
        place_detail_list.setHasFixedSize(true)
        place_detail_list.isNestedScrollingEnabled = false
    }

    private fun showPlaceDetail(placeDetail: PlaceDetail) {
        tv_title.text = placeDetail.title
        tv_hash.text = placeDetail.hash
        tv_desc.text = placeDetail.desc
        img_place.setImageUrl(this, placeDetail.imageUrl)
        place_detail_list.adapter = PlaceDetailAdapter(placeDetail)
    }

    companion object {

        val TAG = PlaceDetailActivity::class.java.simpleName
    }
}