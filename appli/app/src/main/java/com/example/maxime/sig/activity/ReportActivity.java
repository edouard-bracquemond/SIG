package com.example.maxime.sig.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.maxime.sig.R;
import com.example.maxime.sig.adapter.CustomAdapter;
import com.example.maxime.sig.adapter.ReportAdapter;
import com.example.maxime.sig.api.Api;
import com.example.maxime.sig.model.Report;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportActivity extends AppCompatActivity {

    private ReportAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        progressDoalog = new ProgressDialog(ReportActivity.this);
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
        int id = intent.getIntExtra("id",0);
        String equipement = intent.getStringExtra("eq");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://psigo.beta9.ovh/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call call = api.reportList("Bearer "+token, equipement, id);


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Report> list = (List<Report>) response.body();
                if(response.code() == 200){
                    progressDoalog.dismiss();
                    if (list.size()>0)
                        generateDataList((List<Report>) response.body());
                    else{
                        Toast.makeText(ReportActivity.this,
                                "Aucun signalement", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(ReportActivity.this, "Une erreur s'est produite",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Report> reportList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new ReportAdapter(this,reportList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(ReportActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
