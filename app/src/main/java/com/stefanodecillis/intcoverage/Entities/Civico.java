package com.stefanodecillis.intcoverage.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class Civico {

    @SerializedName("Civico")
    String civico;

    @SerializedName("url")
    String url;

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
