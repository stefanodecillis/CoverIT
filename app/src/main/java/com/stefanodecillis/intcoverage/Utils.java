package com.stefanodecillis.intcoverage;


import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class Utils {
    static ArrayClassUtil arrayClassUtil;

    static ArrayList<Provincia> province;
    static ArrayList<Città> comuni;
    static ArrayList<Strada> strade;
    static ArrayList<Civico> civici;


    static String findCountry = "";
    static String findCity = "";
    static String findAddr = "";
    static String findNumHouse = "";

    static ArrayList<String> provList = new ArrayList<String>();
    static ArrayList<String> comList = new ArrayList<String>();
    static ArrayList<String> addrList = new ArrayList<String>();
    static ArrayList<String> numList = new ArrayList<String>();

    static ArrayAdapter<String> provAdapter;
    static ArrayAdapter<String> comAdapter;
    static ArrayAdapter<String> addrAdapter;
    static ArrayAdapter<String> numAdapter;

}
