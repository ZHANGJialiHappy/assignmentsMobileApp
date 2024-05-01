package dk.itu.webview
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val webView: WebView = findViewById(R.id.webArea)
    webView.webViewClient = WebViewClient()
    webView.settings.javaScriptEnabled = true

    val url = "https://www.itu.dk"
    webView.loadUrl(url)
  }
}