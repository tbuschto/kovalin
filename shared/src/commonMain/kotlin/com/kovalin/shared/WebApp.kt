package com.kovalin.shared


object WebApp {

  fun start(webView: WebView) {
    webView.loadResource("www/index.html")
  }

}
