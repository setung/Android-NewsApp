package com.setung.android.newsapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsRetrofit {
    fun getService() : NewsAPIService = retrofit.create(NewsAPIService::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}