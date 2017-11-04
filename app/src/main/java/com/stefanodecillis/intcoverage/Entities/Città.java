package com.stefanodecillis.intcoverage.Entities;
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

    public Strada getStrada() {
        return strada;
    }

    public void setStrada(Strada strada) {
        this.strada = strada;
    }

    public ArrayList<Strada> getStrade() {
        return strade;
    }

    public void setStrade(ArrayList<Strada> strade) {
        this.strade = strade;
    }
}
