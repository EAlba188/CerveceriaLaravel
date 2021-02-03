package com.example.cervecerialaravel.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.model.client.CervezaClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private CervezaClient cervezaClient;
    MutableLiveData<List<Cerveza>> listMutableLiveDataCerveza = new MutableLiveData<>();
    MutableLiveData<Long> idInsertBeer = new MutableLiveData<>();
    Cerveza savedBeer;



    public Repository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://informatica.ieszaidinvergeles.org:9021/laraveles/cerveceria2/public/api/").addConverterFactory(GsonConverterFactory.create()).build();
        cervezaClient = retrofit.create(CervezaClient.class);

    }

    public Cerveza getSavedBeer() {
        return savedBeer;
    }

    public void setSavedBeer(Cerveza savedBeer) {
        this.savedBeer = savedBeer;
    }

    public void updateCerveza(long id, Cerveza cerveza){
        Call<Integer> call = cervezaClient.putCerveza(id, cerveza);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void deleteCerveza(long id){
        Call<Long> call = cervezaClient.deleteCerveza(id);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                idInsertBeer.postValue(response.body());

            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
            }
        });
    }

    public void getAllCervezas(){
        Call<List<Cerveza>> call = cervezaClient.getCerveza();
        call.enqueue(new Callback<List<Cerveza>>() {
            @Override
            public void onResponse(Call<List<Cerveza>> call, Response<List<Cerveza>> response) {
                listMutableLiveDataCerveza.setValue(response.body());
            }


            @Override
            public void onFailure(Call<List<Cerveza>> call, Throwable t) {
            }
        });
    }

    public void insertCerveza(Cerveza cerveza){
        Call<Long> call = cervezaClient.postCerveza(cerveza);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                idInsertBeer.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
            }
        });
    }


    public MutableLiveData<Long> getIdInsertBeer() {
        return idInsertBeer;
    }

    public void setIdInsertBeer(MutableLiveData<Long> idInsertBeer) {
        this.idInsertBeer = idInsertBeer;
    }

    public MutableLiveData<List<Cerveza>> getListMutableLiveDataCerveza() {
        return listMutableLiveDataCerveza;
    }

    public void setListMutableLiveDataCerveza(MutableLiveData<List<Cerveza>> listMutableLiveDataCerveza) {
        this.listMutableLiveDataCerveza = listMutableLiveDataCerveza;
    }


}
