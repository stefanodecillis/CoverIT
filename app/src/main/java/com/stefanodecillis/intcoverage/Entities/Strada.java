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

}
