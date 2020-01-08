package com.example.maxime.sig.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maxime.sig.R;
import com.example.maxime.sig.api.Api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class TreeActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private String photoPath=null;
    private String photoPathShort = null;
    Uri selectedImage;
    private static final int  RETOUR_PRENDRE_PHOTO = 1;
    private static final int RETOUR_SELECT_PHOTO = 2;
    boolean selected = false;
    String seasonSelected=null;
    RecyclerView treesRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int[] myDataset={R.drawable.ic_menu_compass};
    private ImageView imgView;
    private Bitmap image;

    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        imgView = (ImageView)findViewById(R.id.imgViewx);
        Button addPhotoButton = findViewById(R.id.buttonAddPictureID);
        Button takePhotoButton = findViewById(R.id.buttonTakePictureID);
        Button validatePhotoButton = findViewById(R.id.buttonValidatePictureID);
        treesRecyclerView = findViewById(R.id.trees_recycler_view);
        treesRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
       treesRecyclerView.setLayoutManager(layoutManager);
     //   mAdapter = new RecyclerAdapter(myDataset);
        treesRecyclerView.setAdapter(mAdapter);
        Spinner seasonSpinner = findViewById(R.id.spinnerSeasonID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.seasons, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonSpinner.setAdapter(adapter);
        seasonSpinner.setOnItemSelectedListener(this);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }});
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }});
        validatePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoPath!=null) {
                    uploadPhoto();
                }else {
                    Toast.makeText(TreeActivity.this,
                            "Photo absente",
                            Toast.LENGTH_LONG).show();
                }

            }});
    }
    private void takePhoto(){

        Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.FRANCE).format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {

                File photoFile = File.createTempFile("photo"+time,".jpg",photoDir);
                photoPath = photoFile.getAbsolutePath();
                photoPathShort = photoFile.getName();
                Uri photoUri = FileProvider.getUriForFile(TreeActivity.this,TreeActivity.this.getApplicationContext().getPackageName()+".provider",photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intent,RETOUR_PRENDRE_PHOTO);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }
    private void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,RETOUR_SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RETOUR_PRENDRE_PHOTO&&resultCode==RESULT_OK){

            image = BitmapFactory.decodeFile(photoPath);


                    imgView.setImageBitmap(image);


        }
        else if(requestCode==RETOUR_SELECT_PHOTO&&resultCode==RESULT_OK){

         //   if(ActivityCompat.checkSelfPermission(TreeActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)== PERMISSION_GRANTED) {
                selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                photoPath = cursor.getString(columnIndex);

                cursor.close();
                Bitmap image = BitmapFactory.decodeFile(photoPath);
                imgView.setImageBitmap(image);
         //   }
          //  else{
            /*    if(ActivityCompat.shouldShowRequestPermissionRationale(TreeActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                    String[] persmissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(TreeActivity.this,persmissions,3);
                }
                else{

                }
            }*/
        }


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         selected = true;
         seasonSelected = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selected = false;
    }

    private void uploadPhoto(){

        progressDoalog = new ProgressDialog(TreeActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                .baseUrl("https://psigo.beta9.ovh/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        File f= new File(photoPath);
        Log.d("rereifjeifj",photoPathShort);
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        final String token = preferences.getString("token","");
        RequestBody fbody = RequestBody.create(f,MediaType.parse("multipart/form-data"));
        RequestBody sbody = RequestBody.create(seasonSelected,MediaType.parse("multipart/form-data"));
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file",photoPathShort,fbody);

        //Recuperer l'id
        final Intent intent = getIntent();
        int arbre_id = intent.getIntExtra("id",0);

        Call call = api.uploadPicture("Bearer "+token, arbre_id, multipartBody,sbody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                progressDoalog.dismiss();
                if (response.code() == 200){
                    Toast.makeText(TreeActivity.this,
                            "Upload reussi, merci de votre contribution",
                            Toast.LENGTH_LONG).show();
                    Handler myHandler = new Handler();
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoTreeActivity();
                        }
                    },1000);
                }else{
                    Toast.makeText(TreeActivity.this,
                            "Erreur upload",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();

                Toast.makeText(TreeActivity.this,
                        "Erreur upload",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void gotoTreeActivity(){
        Intent i = new Intent(this, NavigationDrawerActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //A B C si D appelle A, B,C,D sont delete
        startActivity(i);
    }

}
