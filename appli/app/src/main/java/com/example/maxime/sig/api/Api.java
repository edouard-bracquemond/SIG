package com.example.maxime.sig.api;


import com.example.maxime.sig.model.AccessToken;
import com.example.maxime.sig.model.Picture;
import com.example.maxime.sig.model.Report;
import com.example.maxime.sig.model.Suggestion;
import com.example.maxime.sig.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {


    @POST("/api/auth/signup")
    Call<User> createUser(
      @Body User User
    );
    @POST("/reports")
    Call<Report>createReport(
        @Header("Authorization") String authorization,
        @Query("comment") String comment,
        @Query("eq") String type,
        @Query("id_eq") int idEquipement

    );
    @POST("/suggestions")
    Call<Suggestion>createSuggestion(
            @Header("Authorization") String authorization,
            @Query("comment") String comment,
            @Query("coords") String coords
    );
    @POST("/api/auth/signin")
    Call<AccessToken> login(
      @Body User user
    );
    @Multipart
    @POST("tree/image/")
    Call<ResponseBody>uploadPicture(

            @Header("Authorization") String authorization,
            @Query("id") int id,
            @Part MultipartBody.Part file,
            @Part("saison") RequestBody saison


            );

    @GET("/tree/images/")
    Call<List<Picture>>pictureList(
            @Header("Authorization") String authorization,
            @Query("id") int id
    );

    @GET("/reports/")
    Call<List<Report>>reportList(
            @Header("Authorization") String authorization,
            @Query("eq") String eq,
            @Query("id") int id
    );



}
