package com.setung.android.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.setung.android.newsapp.Model.Article
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val news = intent.getStringExtra("url")

        val webSettings = dt_webView.settings
        webSettings.javaScriptEnabled=true
        webSettings.javaScriptCanOpenWindowsAutomatically= true
        webSettings.domStorageEnabled=true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls=true
        webSettings.displayZoomControls=false
        webSettings.loadWithOverviewMode=true
        webSettings.cacheMode= WebSettings.LOAD_NO_CACHE
        dt_webView.webViewClient = WebViewClient()
        dt_webView.webChromeClient= WebChromeClient()

        if (news != null) {
            dt_webView.loadUrl(news)
        }


    }
}