package com.kovalin.shared
import android.webkit.WebView as NativeWebView;

actual class WebView actual constructor(native: Any) {

  private val webView: NativeWebView

  init {
    webView = native as android.webkit.WebView
  }

  actual fun loadHtml(html: String) {
    webView.loadData(html, "text/html; charset=UTF-8", null)
  }

}