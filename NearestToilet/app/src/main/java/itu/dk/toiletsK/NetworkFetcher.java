package itu.dk.toiletsK;

import android.location.Location;
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
import java.util.Arrays;

public class NetworkFetcher {
    private static final String TAG = "***N";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                //Log.i(TAG, " "+ connection.getResponseCode());
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
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

    public void fetchItems(String param) {
        //warning may return null
        try {
            String url = Uri.parse(param)
                    .buildUpon()
                    .build().toString();
            //Log.i(TAG, url);
            String jsonString = getUrlString(url);
            //Log.i(TAG, "Received JSON");
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(jsonBody);

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
    }

    private void parseItems(JSONObject jsonBody) throws IOException, JSONException {
        JSONArray featureArray = jsonBody.getJSONArray("features");
        //Log.i("JSON", "l="+ featureArray.length());
        if (featureArray.length() > 0) {
            Toilet[] tempArr = new Toilet[featureArray.length()];
            int s = 0; // counts no of non-null elements in JSON array
            for (int i = 0; (i < featureArray.length()); i++) {
                if (!featureArray.getJSONObject(i).isNull("geometry")) {
                    String roadName = featureArray.getJSONObject(i)
                            .getJSONObject("properties")
                            .getString("vejnavn_husnummer");

                    Location t = new Location("Toilet");
                    t.setLatitude(featureArray.getJSONObject(i)
                            .getJSONObject("properties")
                            .getDouble("latitude"));
                    t.setLongitude(featureArray.getJSONObject(i)
                            .getJSONObject("properties")
                            .getDouble("longitude"));
                    if ((t != null) && (roadName != null)) {
                        tempArr[s] = new Toilet(t, roadName);
                        s = s + 1;
                    }
                }
            }
            ToiletDB.get().setToilets(Arrays.copyOf(tempArr, s));
        }
    }
}
