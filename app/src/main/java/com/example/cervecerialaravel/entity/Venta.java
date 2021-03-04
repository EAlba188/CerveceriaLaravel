package com.example.cervecerialaravel.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;

public class Venta {
    private long id, idCerveza;
    private static int increment = 0;

    public Venta(long idCerveza) {
        this.id = ++increment;
        this.idCerveza = idCerveza;
    }

    public Venta() {
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
