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

    @SerializedName("COPERTURA_BITSTREAM_NGA_VULA_FTTCab")
    private String nga_vula_Status;

    @SerializedName("COPERTURA_BITSTREAM_ASIM_ATM")
    private String asim_atm_status;

    @SerializedName("COPERTURA_BITSTREAM_ASIM_ETHERNET")
    private String asim_eth_Status;

    @SerializedName("COPERTURA_BITSTREAM_SIMM_ATM_2M_4M_6M_8M")
    private String simm_atm_status;

    @SerializedName("COPERTURA_BITSTREAM_SIMM_ETHERNET")
    private String simm_eth_status;

    @SerializedName("COPERTURA_ULL")
    private String ull_Status;

    @SerializedName("COPERTURA_CIRCUITI_TERM_ETH_FIBRA_OTTICA")
    private String term_eth_fibr;

    @SerializedName("COPERTURA_SLU")
    private String slu_status;

    @SerializedName("VELOCITA_DOWN_SLU")
    private String sluDw;

    @SerializedName("VELOCITA_UP_SLU")
    private String sluUp;

    @SerializedName("VELOCITA_DOWN_6dB_BITSTREAM_ASIM_ATM")
    private String asim_atm_6db_down;

    @SerializedName("VELOCITA_UP_6dB_BITSTREAM_ASIM_ATM")
    private String asim_atm_6db_up;

    @SerializedName("VELOCITA_DOWN_11dB_BITSTREAM_ASIM_ATM")
    private String asim_atm_11ddb_down;

    @SerializedName("VELOCITA_UP_11dB_BITSTREAM_ASIM_ATM")
    private String asim_atm_11db_up;

    @SerializedName("VELOCITA_DOWN_12dB_ITSTREAM_ASIM_ATM")
    private String asim_atm_12db_down;

    @SerializedName("VELOCITA_UP_12dB_BITSTREAM_ASIM_ATM")
    private String asim_atm_12db_up;

    @SerializedName("DISTANZA_BITSTREAM_ASIM_ATM")
    private String dist_asim_atm;

    @SerializedName("COPERTURA_BITSTREAM_SIMM_ATM_1M_1-6M_4M_Bonding")
    private String simm_atm_bonding_status;

    @SerializedName("COPERTURA_WLR")
    private String wlr_status;

    @SerializedName("VELOCITA_DOWN_6dB_BITSTREAM_ASIM_ETHERNET")
    private String asim_eth_6db_down;

    @SerializedName("VELOCITA_UP_6dB_BITSTREAM_ASIM_ETHERNET")
    private String asim_eth_6db_up;

    @SerializedName("VELOCITA_DOWN_11dB_BITSTREAM_ASIM_ETHERNET")
    private String asim_eth_11ddb_down;

    @SerializedName("VELOCITA_UP_11dB_BITSTREAM_ASIM_ETHERNET")
    private String asim_eth_11db_up;

    @SerializedName("DISTANZA_BITSTREAM_ASIM_ETHERNET")
    private String dist_asim_eth;


    public String getSimm_atm_bonding_status() {
        return simm_atm_bonding_status;
    }



    public String getWlr_status() {
        return wlr_status;
    }



    public String getAsim_eth_6db_down() {
        return asim_eth_6db_down;
    }



    public String getAsim_eth_6db_up() {
        return asim_eth_6db_up;
    }



    public String getAsim_eth_11ddb_down() {
        return asim_eth_11ddb_down;
    }



    public String getAsim_eth_11db_up() {
        return asim_eth_11db_up;
    }



    public String getAsim_eth_12db_down() {
        return speedAdsl_down;
    }



    public String getAsim_eth_12db_up() {
        return speedAdsl_up;
    }



    public String getDist_asim_eth() {
        return dist_asim_eth;
    }




    public String getAsim_atm_6db_down() {
        return asim_atm_6db_down;
    }

    public String getAsim_atm_6db_up() {
        return asim_atm_6db_up;
    }

    public String getAsim_atm_11ddb_down() {
        return asim_atm_11ddb_down;
    }

    public String getAsim_atm_11db_up() {
        return asim_atm_11db_up;
    }

    public String getAsim_atm_12db_down() {
        return asim_atm_12db_down;
    }

    public String getAsim_atm_12db_up() {
        return asim_atm_12db_up;
    }

    public String getDist_asim_atm() {
        return dist_asim_atm;
    }

    public String getSlu_status() {
        return slu_status;
    }
    public String getSluDw() {
        return sluDw;
    }
    public String getSluUp() {
        return sluUp;
    }
    public String getAsim_atm_status() {
        return asim_atm_status;
    }
    public String getAsim_eth_Status() {
        return asim_eth_Status;
    }
    public String getSimm_atm_status() {
        return simm_atm_status;
    }
    public String getSimm_eth_status() {
        return simm_eth_status;
    }
    public String getUll_Status() {
        return ull_Status;
    }
    public String getTerm_eth_fibr() {
        return term_eth_fibr;
    }
    public String getNga_vula_Status() {
        return nga_vula_Status;
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
