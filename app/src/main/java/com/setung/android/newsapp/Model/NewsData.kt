package com.setung.android.newsapp.Model

import java.io.Serializable

data class NewsData(val status: String, val totalResults: Int, val articles: ArrayList<Article>)

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
): Serializable