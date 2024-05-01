package dk.itu.httpassets
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val AssetsURL = "https://www.staunstrups.dk/jst/assets.txt"
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val result: TextView= findViewById(R.id.assets)
    //This will make a simple TextView Scrollable
    result.movementMethod = ScrollingMovementMethod()

    /*This will give a runtime exception - remove to make example work
    val internet: NetworkFetcher= NetworkFetcher()
    try {
      result.setText(internet.getUrlString(AssetsURL))
    } catch (ioe: IOException) {
    }*/

    //This works
    Thread(HttpThread(AssetsURL, result)).start()
  }
}