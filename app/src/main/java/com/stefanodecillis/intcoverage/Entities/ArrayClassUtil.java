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

    public ArrayList<Provincia> getProvince() {
        return Province;
    }

    public void setProvince(ArrayList<Provincia> province) {
        Province = province;
    }

    public ArrayList<Città> getComuni() {
        return Comuni;
    }

    public void setComuni(ArrayList<Città> comuni) {
        Comuni = comuni;
    }

    public ArrayList<Strada> getStrade() {
        return strade;
    }

    public void setStrade(ArrayList<Strada> strade) {
        this.strade = strade;
    }

    public ArrayList<Civico> getCivici() {
        return civici;
    }

    public void setCivici(ArrayList<Civico> civici) {
        this.civici = civici;
    }

    @SerializedName("Strade")
    ArrayList<Strada> strade;

    @SerializedName("Civici")
    ArrayList<Civico> civici;
}
