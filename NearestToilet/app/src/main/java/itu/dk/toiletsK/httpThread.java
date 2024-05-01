package itu.dk.toiletsK;

import android.location.Location;
import android.util.Log;
import android.webkit.WebView;

public class httpThread implements Runnable {
    // to pass parameter to thread
    String dataUrl;
    WebView ui;
    Location start;

    public httpThread(String url, WebView ui, Location start) {
        this.dataUrl = url;
        this.ui = ui;
        this.start = start;
    }

    public void run() {
        new NetworkFetcher().fetchItems(dataUrl);
        ui.post(new Runnable() {
            @Override
            public void run() {
                Location dest = ToiletDB.get().findClosest(start).getLocation();
                Log.i("***SR", start.toString());
                Log.i("***DR", dest.toString());
                String mapUrl = "https://maps.google.com?saddr=" + start.getLatitude() + "," + start.getLongitude() +
                        "&daddr=" + dest.getLatitude() + "," + dest.getLongitude() + "&dirflg=w";
                ui.loadUrl(mapUrl);
            }
        });
    }
}

