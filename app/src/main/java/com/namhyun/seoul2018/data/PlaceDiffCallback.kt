package com.namhyun.seoul2018.data

import androidx.recyclerview.widget.DiffUtil

class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.title == newItem.title
    }
}