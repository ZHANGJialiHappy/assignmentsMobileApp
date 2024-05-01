package dk.itu.httpassets
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

// inspired by BigNerd Book Chapter 25
class NetworkFetcher {
  fun fetchItems(param: String?): String {
    var result= "empty"
    try {
      val url= Uri.parse(param).buildUpon().build().toString()
      result= getUrlString(url)
    } catch (ioe: IOException) {    }
    return result
  }

  // Fetching result from http request
  @Throws(IOException::class)
  fun getUrlString(urlSpec: String): String {
    val url= URL(urlSpec)
    val connection= url.openConnection() as HttpURLConnection
    return try {
      val out= ByteArrayOutputStream()
      val `in` = connection.inputStream
      if (connection.responseCode != HttpURLConnection.HTTP_OK) {
        throw IOException(connection.responseMessage + ": with " + urlSpec  )
      }
      var bytesRead: Int
      val buffer= ByteArray(1024)
      while (`in`.read(buffer).also { bytesRead = it } > 0) {
        out.write(buffer, 0, bytesRead)
      }
      out.close()
      out.toString()
    } finally {
      connection.disconnect()
    }
  }
}