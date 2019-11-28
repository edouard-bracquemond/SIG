package com.example.maxime.sig.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.maxime.sig.GPS.GPSJScript;
import com.example.maxime.sig.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Environment.getExternalStoragePublicDirectory;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {

            Toast.makeText(getBaseContext(), "No permission 1 ", Toast.LENGTH_LONG).show();
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,42);
        }


        /*Variable recuperant les coordonnees gps*/

        /*Parametrage de la WebView*/
        final WebView myWebView = (WebView) findViewById(R.id.webViewID);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        myWebView.addJavascriptInterface(new GPSJScript(this),"GPS");
        GPSJScript gpsjScript= new GPSJScript(getApplicationContext());



        /*page qui load de base*/
        myWebView.loadUrl("file:///android_asset/arbre.html");



        /*Declaration des boutons*/
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

        Button boutonPhoto = (Button) findViewById(R.id.boutonPhotoID);
        boutonPhoto.setText("Photo");


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



        boutonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
                galleryAddPic();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {}

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );


        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
