package com.example.maxime.sig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        WebView myWebView = new WebView(this.getApplicationContext());
        setContentView(myWebView);
        myWebView.loadUrl("https://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal");
    }
}
