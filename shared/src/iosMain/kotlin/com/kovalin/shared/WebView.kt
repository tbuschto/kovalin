package com.kovalin.shared

import platform.WebKit.WKWebView

actual class WebView actual constructor(native: Any) {

  private val webView: WKWebView

  init {
    webView = native as WKWebView
  }

  actual fun loadHtml(baseUrl: String, html: String) {
    webView.loadHTMLString(html, URL(baseUrl))
  }

  actual fun loadResource(path: String) {
  }

  actual fun loadUrl(url: String) {
  }

}