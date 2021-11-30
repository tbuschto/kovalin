package com.kovalin.shared
import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebViewClient
import android.webkit.WebView as NativeWebView;

class JsBridge(val webView: WebView) {

  /**
   * - Rules: Number and type of arguments must match, even when optional ("?"),
   *   otherwise various errors can be thrown
   * -   null/undefined -> Any? (as null), Boolean (as false), Double (as 0)
   *   - number -> Double, Float, Int (rounded down), Boolean (false) or String
   *   - boolean -> Boolean
   *   - string -> String
   *   - string[] -> Array<String>
   *   - number[], boolean[] not supported?
   *     cordova & capacitor use string only:
   *     https://github.com/apache/cordova-android/blob/a1ed1c0af7c6267f47e580e8850295202692f4ea/cordova-js-src/exec.js#L92
   *     https://github.com/ionic-team/capacitor/blob/9be627d69d8d8c93589469af1069f9fc42bebcc4/android/capacitor/src/main/java/com/getcapacitor/MessageHandler.java#L32
   * - Return type must be any of the following (never Any), otherwise you get empty (?) object
   *   - String -> string
   *   - Int, Float, Double -> number
   *   - Boolean -> boolean
   *   - Arrays not supported?
   */
  @JavascriptInterface
  fun postMessage(args: Array<String>): String {
    try {
      webView.messageHandler.forEach { it(args) }
    } catch (ex: Throwable) {
      return ex.message.toString()
    }
    return "OK"
  }
}

@SuppressLint("NewApi")
actual class WebView actual constructor(native: Any) {

  actual val messageHandler = ArrayList<(message: Array<String>) -> Unit>()

  private val webView: NativeWebView
  private val webViewClient: WebViewClient
  private val bridge: JsBridge = JsBridge(this)

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
    webView.addJavascriptInterface(bridge, "kovalinAndroid")
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
