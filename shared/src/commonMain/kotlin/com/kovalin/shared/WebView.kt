package com.kovalin.shared

expect class WebView(native: Any) {

  val messageHandler: ArrayList<(message: Array<String>) -> Unit>

  fun loadUrl(url: String);

  fun loadResource(path: String);

  fun loadHtml(baseUrl: String, html: String);

}