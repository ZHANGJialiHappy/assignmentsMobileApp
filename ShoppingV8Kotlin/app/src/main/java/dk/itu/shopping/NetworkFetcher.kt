package dk.itu.shopping

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/* Most of this class is copied from
Android Programming: The Big Nerd Ranch Guide
by Bill Phillips, Chris Stewart and Kristin Marsicano Chapter 25
2023 converted to Kotlin
*/
class NetworkFetcher {
    private val TAG = "NetworkFetchr"

    private fun getUrlBytes(urlSpec: String): ByteArray {
        val url = URL(urlSpec)
        val connection = url.openConnection() as HttpURLConnection
        return try {
            val out = ByteArrayOutputStream()
            val `in` = connection.inputStream
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                throw IOException(
                    connection.responseMessage +
                            ": with " + urlSpec
                )
            }
            var bytesRead: Int
            val buffer = ByteArray(1024)
            while (`in`.read(buffer).also { bytesRead = it } > 0) {
                out.write(buffer, 0, bytesRead)
            }
            out.close()
            out.toByteArray()
        } finally {
            connection.disconnect()
        }
    }

    fun fetchItems(url: String, values: ArrayList<Item>, INITIALS: String) {
        try {
            parseItems(String(getUrlBytes(url)), values, INITIALS)
        } catch (je: JSONException) {
            Log.e(TAG, "Failed to parse JSON", je)
        } catch (ioe: IOException) {
            Log.e(TAG, "Failed to fetch items", ioe)
        }
    }

    @Throws(IOException::class, JSONException::class)
    private fun parseItems(jsonString: String, values: ArrayList<Item>, INITIALS: String) {
        if (jsonString.length > 0) {
            val itemsA = JSONArray(jsonString)
            var i = 0
            while (i < itemsA.length()) {
                if (itemsA.getJSONObject(i).getString("who") == INITIALS) //Filter items on INITIALS
                    values.add(
                        Item(
                            itemsA.getJSONObject(i).getString("what"),
                            itemsA.getJSONObject(i).getString("whereC")
                        )
                    )
                i++
            }
        }
    }
}