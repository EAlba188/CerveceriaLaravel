package com.example.cervecerialaravel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cervecerialaravel.entity.Cerveza;
import com.example.cervecerialaravel.entity.Venta;
import com.example.cervecerialaravel.model.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;



    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public void venderCerveza(long id) {
        repository.venderCerveza(id);
    }

    public void getCervezaConcreta(long id) {
        repository.getCervezaConcreta(id);
    }


    public void vender(long id) {
        repository.vender(id);
    }

    public void getAllVentas() {
        repository.getAllVentas();
    }

    public void borrarVenta(long id) {
        repository.borrarVenta(id);
    }

    public MutableLiveData<List<Venta>> getListMutableLiveDataVentas() {
        return repository.getListMutableLiveDataVentas();
    }

    public MutableLiveData<String> getUrl() {
        return repository.getUrl();
    }

    public void updateCerveza(long id, Cerveza cerveza) {
        repository.updateCerveza(id, cerveza);
    }

    public Cerveza getSavedBeer() {
        return repository.getSavedBeer();
    }

    public void setSavedBeer(Cerveza savedBeer) {
        repository.setSavedBeer(savedBeer);
    }

    public void deleteCerveza(long id) {
        repository.deleteCerveza(id);
    }

    public void getAllCervezas() {
        repository.getAllCervezas();
    }

    public MutableLiveData<List<Cerveza>> getListMutableLiveDataCerveza() {
        return repository.getListMutableLiveDataCerveza();
    }

    public void setListMutableLiveDataCerveza(MutableLiveData<List<Cerveza>> listMutableLiveDataCerveza) {
        repository.setListMutableLiveDataCerveza(listMutableLiveDataCerveza);
    }

    public void insertCerveza(Cerveza cerveza) {
        repository.insertCerveza(cerveza);
    }

    public MutableLiveData<Long> getIdInsertBeer() {
        return repository.getIdInsertBeer();
    }

    public void setIdInsertBeer(MutableLiveData<Long> idInsertBeer) {
        repository.setIdInsertBeer(idInsertBeer);
    }
}
