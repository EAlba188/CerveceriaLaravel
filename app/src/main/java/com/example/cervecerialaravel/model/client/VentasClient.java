package com.example.cervecerialaravel.model.client;

import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.entity.Venta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VentasClient {

    @DELETE("ventas/{id}")
    Call<Long> deleteVenta(@Path("id") long id);

    @GET("ventas/{id}")
    Call<Venta> getVenta(@Path("id") long id);

    @GET("ventas")
    Call<List<Venta>> getVenta();

    @POST("ventas")    //INSERT
    Call<Long> postVenta(@Body Venta venta);

    @PUT("ventas/{id}")    //actualizar
    Call<Integer> putVenta(@Path("id") long id, @Body Venta venta);

}
