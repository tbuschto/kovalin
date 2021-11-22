package com.kovalin.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kovalin.shared.WebApp
import com.kovalin.shared.WebView as CommonWebView;
import android.webkit.WebView;

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.hide()
    setContentView(R.layout.activity_main)
    val webView: WebView = findViewById(R.id.webmain)
    val commonWebView = CommonWebView(webView);
    WebApp.start(commonWebView)
  }

}
