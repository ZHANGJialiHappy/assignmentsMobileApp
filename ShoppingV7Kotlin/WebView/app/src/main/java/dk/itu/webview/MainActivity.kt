package dk.itu.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val webView = findViewById<WebView>(R.id.webArea)
    webView.settings.javaScriptEnabled= true
    webView.webViewClient = WebViewClient()
    val url = "https://www.staunstrups.dk/jst/hej.html"
    webView.loadUrl("https://www.itu.dk")
  }
}