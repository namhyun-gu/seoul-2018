package com.namhyun.seoul2018.utilities

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.namhyun.seoul2018.R

fun ImageView.setImageUrl(context: Context, url: String) {
    val options = RequestOptions()
        .placeholder(R.color.image_placeholder)
        .centerCrop()

    Glide.with(context)
        .load(Uri.parse(url))
        .apply(options)
        .into(this)
}