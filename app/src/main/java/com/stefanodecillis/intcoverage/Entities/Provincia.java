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

    public Città getCitta() {
        return citta;
    }

    public void setCitta(Città citta) {
        this.citta = citta;
    }

    public ArrayList<Città> getComuni() {
        return Comuni;
    }

    public void setComuni(ArrayList<Città> comuni) {
        Comuni = comuni;
    }
}
