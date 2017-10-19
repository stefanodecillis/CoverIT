package com.stefanodecillis.intcoverage;

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

    private ArrayList<Civico> civici;

    public ArrayList<Civico> getCivici() {
        return civici;
    }

    public void setCivici(ArrayList<Civico> civici) {
        this.civici = civici;
    }
}
