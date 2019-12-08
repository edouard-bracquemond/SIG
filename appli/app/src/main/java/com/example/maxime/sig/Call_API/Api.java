package com.example.maxime.sig.Call_API;


import com.example.maxime.sig.Model.AccessToken;
import com.example.maxime.sig.Model.User;

import kotlin.ParameterName;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {


    @POST("/api/auth/signup")
    Call<User> createUser(
      @Body User User
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

}
