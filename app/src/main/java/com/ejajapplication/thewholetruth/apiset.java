package com.ejajapplication.thewholetruth;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("login.php")
    Call<responseModel> verifyuser(
            @Field("Username") String userName,
            @Field("Password") String password
    );

    @GET("menu_fetch.php")
    Call<List<category>> getMenu();

    @FormUrlEncoded
    @POST("product.php")
    Call<List<getProduct>> getProduct(
            @Field("MenuId") String MenuId
    );

}
