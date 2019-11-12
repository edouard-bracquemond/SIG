package com.example.maxime.sig.Call_API;

import com.example.maxime.sig.Model.Picture;
import com.example.maxime.sig.Model.Tree;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallAPI {

    @GET("trees")
    Call<List<Tree>> getTrees();
    @GET("trees/{id}/pictures")
    Call<List<Picture>> getPictures(@Path("id")int treeId);

}
