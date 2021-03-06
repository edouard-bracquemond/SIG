package com.example.maxime.sig.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.maxime.sig.gps.GPSJScript;
import com.example.maxime.sig.gps.InfoSelection;
import com.example.maxime.sig.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView myWebView = null;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Parametrage de la WebView*/

        myWebView = (WebView) findViewById(R.id.webViewID);
        myWebView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
        //myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        myWebView.addJavascriptInterface(new GPSJScript(this),"GPS");
        myWebView.addJavascriptInterface(new InfoSelection(this),"InfoSelection");
        GPSJScript gpsjScript= new GPSJScript(getApplicationContext());

        myWebView.loadUrl("file:///android_asset/tout.html");
        getSupportActionBar().setTitle("Les espaces verts");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.suggestioncbID:{
                item.setChecked(!item.isChecked());
                if(item.isChecked()){
                    InfoSelection.addCouche("sigo:suggestion");
                }
                else{
                    InfoSelection.remove("sigo:suggestion");

                }
                myWebView.clearCache(true);
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
            case R.id.arbreID: {
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    InfoSelection.addCouche("sigo:espace_publicev_arbres");
                }else{
                    InfoSelection.remove("sigo:espace_publicev_arbres");
                }
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
            case R.id.bancID: {
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    InfoSelection.addCouche("sigo:espace_public_ev_banc");
                }else{
                    InfoSelection.remove("sigo:espace_public_ev_banc");
                }
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
            case R.id.corbeilleID: {
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    InfoSelection.addCouche("sigo:espace_public_ev_corbeilles");
                }else{
                    InfoSelection.remove("sigo:espace_public_ev_corbeilles");
                }
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
            case R.id.sanitaireID: {
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    InfoSelection.addCouche("sigo:espace_public_ev_sanitaires");
                }else{
                    InfoSelection.remove("sigo:espace_public_ev_sanitaires");
                }
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
            case R.id.dechetID: {
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    InfoSelection.addCouche("sigo:dechets_pav");
                }else{
                    InfoSelection.remove("sigo:dechets_pav");
                }
                myWebView.loadUrl("file:///android_asset/tout.html");
                break;
            }
        }


        if (id == R.id.action_logout){
            logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //A B C si D appelle A, B,C,D sont delete
            startActivity(intent);
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        //moveTaskToBack(true);
        //System.exit(0);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /*
        int id = item.getItemId();

        switch (id){
            case R.id.arbreID:{
                myWebView.loadUrl("file:///android_asset/arbre.html");
                getSupportActionBar().setTitle("Les arbres de l'agglo");
                break;
            }
            case R.id.bancID:{
                myWebView.loadUrl("file:///android_asset/banc.html");
                getSupportActionBar().setTitle("Les bancs de l'agglo");
                break;
            }
            case R.id.corbeilleID:{
                myWebView.loadUrl("file:///android_asset/corbeille.html");
                getSupportActionBar().setTitle("Les poubelles de l'agglo");
                break;
            }
            case R.id.sanitaireID:{
                myWebView.loadUrl("file:///android_asset/sanitaire.html");
                getSupportActionBar().setTitle("Les sanitaires de l'agglo");
                break;
            }
            case R.id.dechetID:{
                myWebView.loadUrl("file:///android_asset/dechet.html");
                getSupportActionBar().setTitle("Le recyclage de l'agglo");
                break;
            }
            case R.id.toutID:{
                myWebView.loadUrl("file:///android_asset/tout.html");
                getSupportActionBar().setTitle("Tout les élèments de l'agglo");
                break;
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }


}
