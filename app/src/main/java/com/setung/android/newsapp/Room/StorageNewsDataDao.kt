package com.setung.android.newsapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.setung.android.newsapp.Model.Article

@Dao
interface StorageNewsDataDao {
    @Query("SELECT * FROM Storage")
    fun getAll(): List<StorageNewsData>

    @Query("SELECT * FROM Storage WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<StorageNewsData>

    @Query("SELECT * FROM Storage WHERE url IN (:url)")
    fun loadAllByUrl(url: String): List<StorageNewsData>

    @Insert
    fun insertAll(vararg users: StorageNewsData)

    @Insert
    fun insert(users: StorageNewsData)

    @Delete
    fun delete(user: StorageNewsData)

    @Query("Delete FROM Storage")
    fun deleteAll()

    @Query("Delete FROM Storage WHERE url IN (:url)")
    fun deleteArtice(url: String)
}
