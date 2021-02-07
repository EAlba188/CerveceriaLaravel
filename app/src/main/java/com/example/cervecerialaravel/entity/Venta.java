package com.example.cervecerialaravel.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;

public class Venta {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("idCerveza")
    @Expose
    private long idCerveza;


    public Venta(long id, long idCerveza) {
        this.id = id;
        this.idCerveza = idCerveza;
    }

    public long getId() {
        return id;
    }

    public long getIdCerveza() {
        return idCerveza;
    }

    public void setIdCerveza(long idTelefono) {
        this.idCerveza = idTelefono;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", idCerveza=" + idCerveza +
                '}';
    }
}
