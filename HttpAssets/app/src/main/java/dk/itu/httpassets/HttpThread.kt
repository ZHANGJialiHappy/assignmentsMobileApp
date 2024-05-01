package dk.itu.httpassets
import android.widget.TextView
import java.lang.Runnable

class HttpThread(private var url: String, private var result: TextView) : Runnable {
  override fun run() {
    val assetString = NetworkFetcher().fetchItems(url)
    result.post { result.text = assetString }
  }
}