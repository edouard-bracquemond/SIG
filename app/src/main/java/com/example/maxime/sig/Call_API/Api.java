package com.example.maxime.sig.Call_API;


import com.example.maxime.sig.Model.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @POST("auth/signup")
    Call<User> createUser(
      @Body User User
    );


}
