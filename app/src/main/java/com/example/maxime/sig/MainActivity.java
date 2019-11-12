package com.example.maxime.sig;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webViewID);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal");


        Button bouton = (Button) findViewById(R.id.boutonTestID);
        bouton.setText("Arbre");
    }

}
