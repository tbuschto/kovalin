package com.kovalin.shared
import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebViewClient
import android.webkit.WebView as NativeWebView;

@SuppressLint("NewApi")
actual class WebView actual constructor(native: Any) {

  private val webView: NativeWebView
  private val webViewClient: WebViewClient

  init {
    webView = native as android.webkit.WebView
    webViewClient = object : WebViewClient() {
      override fun shouldInterceptRequest(
        view: android.webkit.WebView?,
        request: WebResourceRequest?
      ): WebResourceResponse? {
        return super.shouldInterceptRequest(view, request)
      }
    }
    webView.webViewClient = webViewClient
    webView.settings.allowFileAccess = true;
    webView.settings.javaScriptEnabled = true;
  }

  actual fun loadResource(path: String) {
    loadUrl("file:///android_asset/$path")
  }

  actual fun loadUrl(url: String) {
    webView.loadUrl(url)
  }

  actual fun loadHtml(baseUrl: String, html: String) {
    webView.loadDataWithBaseURL(baseUrl, html, "text/html; charset=UTF-8", null, null)
  }

}

fun htmlResponse(content: String): WebResourceResponse {
  val statusCode = 200;
  val reasonPhase = "OK";
  var responseHeaders = HashMap<String, String>();
  responseHeaders["Access-Control-Allow-Origin"] = "*";
  return WebResourceResponse(
    "text/html",
    "UTF-8",
    statusCode,
    reasonPhase,
    responseHeaders,
    content.byteInputStream()
  )
}

fun notFoundResponse(): WebResourceResponse {
  val statusCode = 404;
  val reasonPhase = "Not found";
  var responseHeaders = HashMap<String, String>();
  responseHeaders["Access-Control-Allow-Origin"] = "*";
  return WebResourceResponse(
    "text/html",
    "UTF-8",
    statusCode,
    reasonPhase,
    responseHeaders,
    "".byteInputStream()
  )
}