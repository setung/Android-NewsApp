package com.setung.android.newsapp.Retrofit

import com.setung.android.newsapp.MetaData
import com.setung.android.newsapp.Model.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("/v2/top-headlines")
    fun requestNews(
        @Query("country") country: String=MetaData.NEWS_COUNTRY,
        @Query("category") category: String = "",
        @Query("apiKey") apiKey: String = MetaData.NEWS_API_KEY
    ): Call<NewsData>

}

