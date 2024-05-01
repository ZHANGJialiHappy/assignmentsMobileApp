package itu.dk.showRoute;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class LocationActivity extends Activity {
    // Show route to ITU from Main Station
    private final Location lITU = new Location("IT University");
    private final Location lMainStation = new Location("Main Station");

    private WebView mWeb;

    private void startBrowser(Location start, Location dest) {
        String url = "https://maps.google.com?saddr=" + start.getLatitude() + "," + start.getLongitude() +
                "&daddr=" + dest.getLatitude() + "," + dest.getLongitude() + "&dirflg=w";
        mWeb.loadUrl(url);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        //Set Coordinates for ITU
        lITU.setLatitude(55.659886);
        lITU.setLongitude(12.591235);

        //Set Coordinates for Main Train Station
        lMainStation.setLatitude(55.67594);
        lMainStation.setLongitude(12.56553);

        mWeb = findViewById(R.id.webpage);
        mWeb.getSettings().setJavaScriptEnabled(true);
        startBrowser(lMainStation, lITU);
    }
}

