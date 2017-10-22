package com.stefanodecillis.intcoverage;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stefanodecillis on 21/10/2017.
 */

public class InfoLine {

    @SerializedName("PART_TOP_CLIENTE")
    private String topAddress;

    @SerializedName("STRADA_CLIENTE")
    private String address;

    @SerializedName("CIVICO_CLIENTE")
    private String num;

    @SerializedName("COMUNE_CLIENTE")
    String city;

    public String nameLine () {
        return (topAddress+" "+address+" "+" "+num);
    }

    @SerializedName("VELOCITA_DOWN_12_dB_BITSTREAM_ASIM_ETHERNET")
    private String speedAdsl_down;

    @SerializedName("VELOCITA_UP_12dB_BITSTREAM_ASIM_ETHERNET")
    private String speedAdsl_up;

    @SerializedName("DISTANZA_BITSTREAM_NGA_VULA_FTTCab")
    private String distCab;

    @SerializedName("EDR_SLU")
    private String fttc;

    @SerializedName("COPERTURA_EVDSL_BITSTREAM_NGA_VULA_FTTCab")
    private String vdslStatus;

    @SerializedName("VELOCITA_DOWN_EVDSL_BITSTREAM_NGA_VULA_FTTCab")
    private String speedVdsl_down;

    @SerializedName("VELOCITA_UP_EVDSL_BITSTREAM_NGA_VULA_FTTCab")
    private String speedVdsl_up;

    @SerializedName("VELOCITA_DOWN_SLU")
    private String speedSlu_down;

    @SerializedName("VELOCITA_UP_SLU")
    private String speedSlu_up;

    public String getSpeedSlu_down() {
        return speedSlu_down;
    }
    public String getSpeedSlu_up() {
        return speedSlu_up;
    }
    public String getSpeedAdsl_down() {
        return speedAdsl_down;
    }
    public String getSpeedAdsl_up() {
        return speedAdsl_up;
    }
    public String getDistCab() {
        return distCab;
    }
    public String getFttc() {
        return fttc;
    }
    public String getVdslStatus() {
        return vdslStatus;
    }
    public String getSpeedVdsl_down() {
        return speedVdsl_down;
    }
    public String getSpeedVdsl_up() {
        return speedVdsl_up;
    }

}
