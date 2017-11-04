package com.stefanodecillis.intcoverage.Entities;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class ArrayClassUtil {

    @SerializedName("Province")
    ArrayList<Provincia> Province;

    @SerializedName("Comuni")
    ArrayList<Città> Comuni;

    @SerializedName("Strade")
    ArrayList<Strada> strade;

    @SerializedName("Civici")
    ArrayList<Civico> civici;
}
