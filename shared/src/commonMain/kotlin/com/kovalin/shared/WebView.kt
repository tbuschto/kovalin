package com.kovalin.shared

expect class WebView(native: Any) {

  fun loadHtml(html: String);

}