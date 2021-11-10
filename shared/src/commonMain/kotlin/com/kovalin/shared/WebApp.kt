package com.kovalin.shared


object WebApp {

    fun start(webView: WebView) {
        webView.loadHtml("""
          <html>
            <body>
              <div>Hello World</div>
            </body>
          </html>
        """.trimIndent())
    }

}
