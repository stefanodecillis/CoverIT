package com.stefanodecillis.intcoverage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class ArrayClassUtil {

    @SerializedName("Province")
    ArrayList<Provincia> province;

    @SerializedName("Comuni")
    ArrayList<Città> comuni;

    @SerializedName("Strade")
    ArrayList<Strada> strade;

    @SerializedName("Civici")
    ArrayList<Civico> civici;
}
