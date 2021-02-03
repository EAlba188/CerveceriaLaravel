package com.example.cervecerialaravel.model.client;

import com.example.cervecerialaravel.entity.Cerveza;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CervezaClient {

    //database routes api - Ruta laravel para ver la ruta
    @DELETE("cerveza/{id}")
    Call<Long> deleteCerveza(@Path("id") long id);

    @GET("cerveza/{id}")
    Call<Cerveza> getCerveza(@Path("id") long id);

    @GET("cerveza")
    Call<List<Cerveza>> getCerveza();

    @POST("cerveza")    //INSERT
    Call<Long> postCerveza(@Body Cerveza cerveza);

    @PUT("cerveza/{id}")    //actualizar
    Call<Integer> putCerveza(@Path("id") long id, @Body Cerveza cerveza);


}
