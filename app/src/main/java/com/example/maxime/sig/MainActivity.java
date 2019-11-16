package com.example.maxime.sig;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView myWebView = (WebView) findViewById(R.id.webViewID);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        /*page qui load de base*/
        myWebView.loadUrl("file:///android_asset/arbre.html");

        Button boutonArbre = (Button) findViewById(R.id.boutonArbreID);
        boutonArbre.setText("Arbre");
        Button boutonCorbeille = (Button) findViewById(R.id.boutonCorbeilleID);
        boutonCorbeille.setText("Corbeille");
        Button boutonDechet = (Button) findViewById(R.id.boutonDechetID);
        boutonDechet.setText("Dechet");
        Button boutonSanitaire = (Button) findViewById(R.id.boutonSanitaireID);
        boutonSanitaire.setText("Sanitaire");
        Button boutonBanc = (Button) findViewById(R.id.boutonBancID);
        boutonBanc.setText("Banc");



        boutonArbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebView.loadUrl("file:///android_asset/arbre.html");
            }
        });

        boutonBanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebView.loadUrl("file:///android_asset/banc.html");
            }
        });

        boutonCorbeille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebView.loadUrl("file:///android_asset/corbeille.html");
            }
        });

        boutonDechet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebView.loadUrl("file:///android_asset/dechet.html");
            }
        });

        boutonSanitaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebView.loadUrl("file:///android_asset/sanitaire.html");
            }
        });

    }


}
