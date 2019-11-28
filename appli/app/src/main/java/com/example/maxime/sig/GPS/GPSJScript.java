package com.example.maxime.sig.GPS;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

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
            latitude=1;
            longitude=1;
        }
        return latitude+" "+ longitude;
    }


}
