package com.stefanodecillis.intcoverage.Entities;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by stefanodecillis on 18/10/2017.
 */

public class Provincia {

    @SerializedName("CodiceIstatProvincia")
    int id;

    @SerializedName("Provincia")
    String name;

    @SerializedName("url")
    String url;

    @SerializedName("Comune")
    Città citta;

    @SerializedName("Comuni")
    ArrayList<Città> Comuni;
}
