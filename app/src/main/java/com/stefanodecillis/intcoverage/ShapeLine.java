package com.stefanodecillis.intcoverage;

/**
 * Created by stefanodecillis on 15/10/2017.
 */

public class ShapeLine {

    //Variables with underscores aren't compliant in Java, this is not C based.
    private String address = "";
    private String fttc = "";
    private String dist_cab = "";
    private String adsl_Dw = "";
    private String adsl_Up = "";
    private String vdslStatus = "";
    private String vdsl_Dw = "";
    private String vdsl_Up = "";

    public ShapeLine() {
    }


    //getters and setters
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFttc() {
        return fttc;
    }
    public void setFttc(String fttc) {
        this.fttc = fttc;
    }

    //Non-compliant method name. No underscores.
    public String getDist_cab() {
        return dist_cab;
    }
    //Non-compliant method name. No underscores.
    public void setDist_cab(String dist_cab) {
        this.dist_cab = dist_cab;
    }
    //Non-compliant method name. No underscores.
    public String getAdsl_Dw() {
        return adsl_Dw;
    }
    public void setAdsl_Dw(String adsl_Dw) {
        this.adsl_Dw = adsl_Dw;
    }
    //Non-compliant method name. No underscores.
    public String getAdsl_Up() {
        return adsl_Up;
    }
    //Non-compliant method name. No underscores.
    public void setAdsl_Up(String adsl_Up) {
        this.adsl_Up = adsl_Up;
    }

    public String getVdslStatus() {
        return vdslStatus;
    }
    public void setVdslStatus(String vdslStatus) {
        this.vdslStatus = vdslStatus;
    }

    //Non-compliant method name. No underscores.
    public String getVdsl_Dw() {
        return vdsl_Dw;
    }
    //Non-compliant method name. No underscores.
    public void setVdsl_Dw(String vdsl_Dw) {
        this.vdsl_Dw = vdsl_Dw;
    }

    //Non-compliant method name. No underscores.
    public String getVdsl_Up() {
        return vdsl_Up;
    }
    //Non-compliant method name. No underscores.
    public void setVdsl_Up(String vdsl_Up) {
        this.vdsl_Up = vdsl_Up;
    }



}
