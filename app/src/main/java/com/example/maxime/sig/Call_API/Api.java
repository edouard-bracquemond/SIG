package com.example.maxime.sig.Call_API;


import com.example.maxime.sig.Model.AccessToken;
import com.example.maxime.sig.Model.User;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {


    @POST("auth/signup")
    Call<User> createUser(
      @Body User User
    );
    @POST("auth/signin")
    Call<AccessToken> login(
      @Body User user
    );
    @Multipart
    @POST("upload")
    Call<ResponseBody>uploadPicture(
     @Part MultipartBody.Part file
    );

}
