package com.example.maxime.sig.api;


import com.example.maxime.sig.model.Picture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    public ArrayList<Picture> pictures;

    public Service(){
        pictures = new ArrayList<Picture>();
    }
    /**
     * Cette fonction nous permet de recuperer la liste des images associées à un arbre
     * @param token
     * @param arbre_id
     */
    public void imageList(String token, int arbre_id){
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


        call.enqueue(new Callback () {
            @Override
            public void onResponse(Call call, Response response) {
                Service.this.pictures = (ArrayList<Picture>) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
