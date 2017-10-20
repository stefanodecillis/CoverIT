package com.stefanodecillis.intcoverage;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by stefanodecillis on 18/10/2017.
 */

public class Città {

    @SerializedName("CodiceIstatComune")
    int id;

    @SerializedName("Comune")
    String name;

    String url;

    @SerializedName("Strada")
    Strada strada;

    @SerializedName("Strade")
    ArrayList<Strada> strade;
}
