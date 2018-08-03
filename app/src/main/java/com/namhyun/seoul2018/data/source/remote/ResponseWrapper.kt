package com.namhyun.seoul2018.data.source.remote

data class ResponseWrapper<T>(val status: String, val data: T)