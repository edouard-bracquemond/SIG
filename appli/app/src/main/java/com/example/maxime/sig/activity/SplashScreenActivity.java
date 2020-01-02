
package com.example.maxime.sig.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.maxime.sig.R;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SplashScreenActivity extends AppCompatActivity {

    private int TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {

            Toast.makeText(getBaseContext(), "Geoloc conseillée", Toast.LENGTH_LONG).show();
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,42);
        }

        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callMainActivity();
            }
        },TIME);
    }


    public void callMainActivity(){
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
