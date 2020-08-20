package com.setung.android.newsapp.Storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setung.android.newsapp.R

class StorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        supportActionBar?.hide()

    }
}