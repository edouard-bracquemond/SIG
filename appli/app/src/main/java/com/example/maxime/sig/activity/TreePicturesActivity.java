package com.example.maxime.sig.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.Toast;

import com.example.maxime.sig.R;
import com.example.maxime.sig.adapter.CustomAdapter;
import com.example.maxime.sig.api.Api;
import com.example.maxime.sig.model.Picture;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TreePicturesActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_pictures);

        progressDoalog = new ProgressDialog(TreePicturesActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        String token = "";
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        if (preferences.contains("token")) {
            token = getSharedPreferences("myPrefs", MODE_PRIVATE).getAll().get("token").toString();
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //A B C si D appelle A, B,C,D sont delete
            startActivity(intent);
        }

        //Recuperer l'id
        final Intent intent = getIntent();
        int arbre_id = intent.getIntExtra("id",0);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://psigo.beta9.ovh/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call call = api.pictureList("Bearer "+token, arbre_id);


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Picture> list = (List<Picture>) response.body();
                if(response.code() == 200){
                    progressDoalog.dismiss();
                    if (list.size()>0)
                        generateDataList((List<Picture>) response.body());
                    else{
                        Toast.makeText(TreePicturesActivity.this,
                                "Aucune image", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(TreePicturesActivity.this, "Une erreur s'est produite",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Picture> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(TreePicturesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }
}
