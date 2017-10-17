package com.stefanodecillis.intcoverage;

import java.util.ArrayList;

/**
 * Created by stefanodecillis on 12/10/2017.
 */

public class Tools {

    //Missing final modifier
    static public String URLMAP = "http://marlin.planetel.it/netmap/";

    /*
    *
    * Those shan't be shared! Bad quality code. consider using getters/setters?
    *
    * Tools isn't supposed to contain variables. Would "Utils" be better suited?
    *
    */

    //???
    static ArrayList<String> countryName = new ArrayList<String>();
    static ArrayList<String> cityName = new ArrayList<String>();
    static ArrayList<String> addressName = new ArrayList<String>();
    static ArrayList<String> listNum = new ArrayList<String>();

    //???
    static boolean foundCountry = false;
    static boolean foundCity = false;
    static boolean foundAddr = false;
    static boolean foundNum = false;
    static boolean resultFound = false;
    static boolean trueSearch = false;
    static boolean errorShw = true;

}
