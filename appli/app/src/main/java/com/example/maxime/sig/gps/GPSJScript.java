package com.example.maxime.sig.gps;

import android.content.Context;
import android.location.Location;
import android.webkit.JavascriptInterface;

public class GPSJScript  {
    Context mContext;
    public GPSJScript(Context c)
    {
        mContext = c;
    }

    @JavascriptInterface
    public String getFromAndroid() {
        Tracker tracker=new Tracker(mContext);
        Location location=tracker.getLocation();
        double latitude;
        double longitude;
        if (location!=null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        else{
            //On se positionne sur les coordonées de la mairie d'Orléans
            latitude=47.902815;
            longitude=1.908836;
        }
        return latitude+" "+ longitude;
    }


}
