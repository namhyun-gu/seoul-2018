package com.namhyun.seoul2018.data.source.remote

import com.namhyun.seoul2018.data.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SeoulService {

    @POST("/main/getPlace")
    fun getPlace(@Query("page") page: Int): Call<ResponseWrapper<List<Place>>>

    @POST("/main/getPlaceDistance")
    fun getPlaceWithDistance(@Query("lat") lat: Long, @Query("lng") lon: Long): Call<ResponseWrapper<List<Place>>>

    @POST("/main/getPlaceAll")
    fun getAllPlace(): Call<ResponseWrapper<List<Place>>>

    @GET("/public/front/pc/html/file{id}.html")
    fun getPlaceDetail(@Path("id") id: Int): Call<String>
}