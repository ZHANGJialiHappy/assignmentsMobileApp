package dk.itu.busdepart;
import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkFetcher {
  private static final String TAG = "NetworkFetchr";

  public byte[] getUrlBytes(String urlSpec) throws IOException {
    URL url = new URL(urlSpec);
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = connection.getInputStream();
      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
      }
      int bytesRead;
      byte[] buffer = new byte[1024];
      while ((bytesRead = in.read(buffer)) > 0) {
        Log.i("GetBytes", "once more");
        out.write(buffer, 0, bytesRead);
      }
      out.close();
      return out.toByteArray();
    } finally {
      connection.disconnect();
    }
  }
  public String getUrlString(String urlSpec) throws IOException {
    return new String(getUrlBytes(urlSpec));
  }
  public String fetchItems(String param) {
    //warning may return null
    try {
      String url = Uri.parse(param)
          .buildUpon()
          //.appendQueryParameter("apikey", APIKey)
          .build().toString();
      Log.i(TAG, url);
      String jsonString = getUrlString(url);
      Log.i(TAG, "Received JSON");
      JSONObject jsonBody = new JSONObject(jsonString);
      return parseItems(jsonBody);
    } catch (JSONException je) {
      Log.e(TAG, "Failed to parse JSON", je);
      return null;
    } catch (IOException ioe) {
      Log.e(TAG, "Failed to fetch items", ioe);
      return null;
    }
  }

  private String parseItems(JSONObject jsonBody) throws IOException, JSONException {
    JSONObject depBoard= jsonBody.getJSONObject("DepartureBoard");
    JSONArray depArray= depBoard.getJSONArray("Departure");
    Log.i("JSON", "l="+ depArray.length());
    if (depArray.length()>0) {
      String res= ""; int found= 0;
      for (int i=0; ((i<depArray.length() && (found<4))); i++) {  // Here the number of departures (4) can be changed
        String bName= depArray.getJSONObject(i).getString("name");
        if  ( (bName.equals("Bus 33")) ){
          res = res + "\n" +bName +": "+ depArray.getJSONObject(i).getString("time") +
              " mod " + depArray.getJSONObject(i).getString("finalStop");
          found= found+1;
        }
      }
      return res;
    } else return null;
  }
}