package com.namhyun.seoul2018

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.namhyun.seoul2018.data.PlaceDiffCallback
import com.namhyun.seoul2018.ui.place.PlaceAdapter
import com.namhyun.seoul2018.ui.place.PlaceViewModel
import com.namhyun.seoul2018.utilities.ItemOffsetDecoration
import com.namhyun.seoul2018.utilities.TYPE_PADDING
import com.namhyun.seoul2018.utilities.supportTranslucentStatus
import kotlinx.android.synthetic.main.activity_place.*

class PlaceActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaceViewModel
    private lateinit var adapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        initViews()
        viewModel = ViewModelProviders.of(this).get(PlaceViewModel::class.java)
        viewModel.places.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.requestPlaces()
    }

    private fun initViews() {
        toolbar.supportTranslucentStatus(this, TYPE_PADDING)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        adapter = PlaceAdapter(this, PlaceDiffCallback())
        place_list.layoutManager = LinearLayoutManager(this)
        place_list.setHasFixedSize(true)
        place_list.adapter = adapter
        place_list.addItemDecoration(ItemOffsetDecoration(16))

        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_info -> {
                    startActivity(Intent(this, InfoActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        val TAG = PlaceActivity::class.java.simpleName
    }
}
