package com.example.maxime.sig.GPS;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class Tracker implements LocationListener {
    private Context mContext;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;


    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    private LocationManager locationManager;

    public Tracker(Context context){
        mContext=context;
    }

    public Location getLocation(){
        Location location=null;

        LocationManager locationManager=(LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (
                (isGPSEnabled||isNetworkEnabled)
                && (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        )

        {
            if(isNetworkEnabled){
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                         3000,
                         10,
                         this);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                Log.i("TAG",location.getLatitude()+""+location.getLongitude());
            }else if (isGPSEnabled){
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        3000,
                        10,
                        this
                );
                location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }

        return location;

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {


    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(mContext, "Gps turned on", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(mContext, "Gps turned off ", Toast.LENGTH_LONG).show();

    }
}
