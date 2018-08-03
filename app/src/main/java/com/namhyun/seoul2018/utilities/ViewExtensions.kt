package com.namhyun.seoul2018.utilities

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringDef

const val TYPE_PADDING = "padding"
const val TYPE_MARGIN = "margin"

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    TYPE_MARGIN,
    TYPE_PADDING
)
annotation class ViewType

fun View.supportTranslucentStatus(context: Context, @ViewType type: String) {
    val statusHeight = with(context) {
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) resources.getDimensionPixelSize(resId)
        else 0
    }
    when (type) {
        TYPE_PADDING -> setPadding(0, statusHeight, 0, 0)
        else -> {
            val params = layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(0, statusHeight, 0, 0)
            layoutParams = params
        }
    }
}