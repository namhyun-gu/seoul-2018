package com.namhyun.seoul2018.ui.placedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namhyun.seoul2018.R
import com.namhyun.seoul2018.data.PlaceDetail
import kotlinx.android.synthetic.main.item_place_detail.view.*

class PlaceDetailAdapter(private val placeDetail: PlaceDetail) :
    RecyclerView.Adapter<PlaceDetailAdapter.PlaceDetailViewHolder>() {
    private var itemSet: List<Pair<Int, String>> = emptyList()

    init {
        with(placeDetail) {
            itemSet += Pair(R.drawable.ic_location, address)
            if (findBusWay != null) itemSet += Pair(R.drawable.ic_directions_bus, findBusWay)
            if (findMetroWay != null) itemSet += Pair(R.drawable.ic_directions_subway, findMetroWay)
            if (workTime != null) itemSet += Pair(R.drawable.ic_access_time, workTime)
            if (price != null) itemSet += Pair(R.drawable.ic_money, price)
            if (homeUrl != null) itemSet += Pair(R.drawable.ic_web, homeUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceDetailViewHolder =
        PlaceDetailViewHolder(parent)

    override fun onBindViewHolder(holder: PlaceDetailViewHolder, position: Int) {
        val item = itemSet[position]
        holder.imgIcon.setImageResource(item.first)
        holder.tvContent.text = item.second
    }

    override fun getItemCount(): Int = itemSet.size

    inner class PlaceDetailViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context!!)
            .inflate(R.layout.item_place_detail, parent, false)
    ) {
        val imgIcon = itemView.img_icon
        val tvContent = itemView.tv_content
    }
}