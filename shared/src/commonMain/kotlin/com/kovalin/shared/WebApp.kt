package com.kovalin.shared


object WebApp {

  fun start(webView: WebView) {
    webView.messageHandler.add { println(it[0]) }
    webView.loadResource("www/index.html")
  }

}
