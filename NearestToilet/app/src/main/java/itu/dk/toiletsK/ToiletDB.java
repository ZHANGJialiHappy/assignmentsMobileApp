package itu.dk.toiletsK;

import android.location.Location;

public class ToiletDB {
    //Singleton class shared between main activity and background thread
    private static ToiletDB sToiletDB;
    private Toilet[] toilets;

    private ToiletDB() {
    }

    public static ToiletDB get() {
        if (sToiletDB == null) sToiletDB = new ToiletDB();
        return sToiletDB;
    }

    public void setToilets(Toilet[] toilets) {
        this.toilets = toilets;
    }

    private double distance(Toilet p1, Location p2) {
        return p1.getLocation().distanceTo(p2);
    }

    public Toilet findClosest(Location target) {
        double temp;
        if ((toilets != null) && (toilets.length > 0)) {
            int i = toilets.length - 1;
            Toilet closest = toilets[i];
            //Log.i("TEST", toilets[i].toString()+"xxx"+target.toString());
            double min = distance(closest, target);
            while (i > 0) {
                i = i - 1;
                if (!toilets[i].getmStreet().equals("null")) {
                    temp = distance(toilets[i], target);
                    if (temp < min) {
                        closest = toilets[i];
                        min = temp;
                    }
                }
            }
            return closest;
        }
        return null;
    }
}
