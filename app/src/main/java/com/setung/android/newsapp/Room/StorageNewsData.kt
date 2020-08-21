package com.setung.android.newsapp.Room

import androidx.room.*
import com.setung.android.newsapp.Model.Article
import com.setung.android.newsapp.Model.NewsData

@Entity(tableName = "storage")
data class StorageNewsData(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?
) {
    constructor(data: Article) :
            this(uid = null, title = data.title, url = data?.url, urlToImage = data?.urlToImage)

}


