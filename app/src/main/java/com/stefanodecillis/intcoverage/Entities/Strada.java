package com.stefanodecillis.intcoverage.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class Strada {

    @SerializedName("CodiceStrada")
    int id;

    @SerializedName("Strada")
    String name;

    String url;

    @SerializedName("Civici")
    ArrayList<Civico> civici;

    @SerializedName("Civico")
    Civico civico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Civico> getCivici() {
        return civici;
    }

    public void setCivici(ArrayList<Civico> civici) {
        this.civici = civici;
    }

    public Civico getCivico() {
        return civico;
    }

    public void setCivico(Civico civico) {
        this.civico = civico;
    }
}
