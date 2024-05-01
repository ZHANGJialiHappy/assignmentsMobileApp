package itu.dk.toiletsK;

import android.location.Location;

/**
 * Created by jst on 3/24/16.
 */
public class Toilet {
    private Location mLoc;  // GPS position
    private String mStreet; //Street name

    public Toilet(Location loc, String mStreet) {
        mLoc = loc;
        setmStreet(mStreet);
    }

    public Toilet(Location loc) {
        mLoc = loc;
        setmStreet("  ");
    }

    public String getmStreet() {
        return mStreet;
    }

    public void setmStreet(String mStreet) {
        if (mStreet != null) this.mStreet = mStreet;
        else this.mStreet = "No name";
    }

    public Location getLocation() {
        return mLoc;
    }

    @Override
    public String toString() {
        return mStreet + " la= " + mLoc.getLatitude() + ", lo= " + mLoc.getLongitude();
    }
}