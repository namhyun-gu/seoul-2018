package com.namhyun.seoul2018.ui.place

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.namhyun.seoul2018.PlaceDetailActivity
import com.namhyun.seoul2018.R
import com.namhyun.seoul2018.data.Place
import com.namhyun.seoul2018.utilities.BASE_URL
import com.namhyun.seoul2018.utilities.KEY_PLACE_ID
import com.namhyun.seoul2018.utilities.setImageUrl
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceAdapter(val context: Context, itemCallback: DiffUtil.ItemCallback<Place>) :
    ListAdapter<Place, PlaceAdapter.LocationViewHolder>(itemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(parent)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position).let { place ->
            with(holder) {
                imgLocation.setImageUrl(context, BASE_URL + place.imageUrl)
                tvTitle.text = place.title
                tvDesc.text = place.desc
                contentLayout.setOnClickListener {
                    val intent = Intent(context, PlaceDetailActivity::class.java)
                    intent.putExtra(KEY_PLACE_ID, place.id)
                    context.startActivity(intent)
                }
            }
        }
    }

    inner class LocationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context!!)
            .inflate(R.layout.item_place, parent, false)
    ) {
        val contentLayout = itemView.content_layout
        val imgLocation = itemView.img_place
        val tvTitle = itemView.tv_title
        val tvDesc = itemView.tv_desc
    }
}