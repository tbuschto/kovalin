package com.kovalin.shared

expect class WebView(native: Any) {

  fun loadUrl(url: String);

  fun loadResource(path: String);

  fun loadHtml(baseUrl: String, html: String);

}