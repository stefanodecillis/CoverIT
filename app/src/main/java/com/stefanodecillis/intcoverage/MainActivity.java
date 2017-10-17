package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //objectUI
    protected FloatingActionButton findBtn = null;
    protected AutoCompleteTextView autocomplete = null;
    protected AutoCompleteTextView autocompleteCity = null;
    protected AutoCompleteTextView autocompleteAddr = null;
    protected AutoCompleteTextView autocompleteNum = null;


    String findCountry = "";
    String findCity = "";
    String findAddr = "";
    String findNumHouse = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        Tools.trueSearch = false;
        callApi();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Tools.countryName
        );
        final ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Tools.cityName
        );

        final ArrayAdapter<String> adapterAddr = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Tools.addressName
        );
        final ArrayAdapter<String> adapterNum = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Tools.listNum
        );
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                findCountry = autocomplete.getText().toString();
                Tools.trueSearch = false;
                Tools.foundCountry = true;
                Tools.foundCity = false;
                Tools.foundNum = false;
                autocompleteCity.setText("");
  //SISTEMARE ADAPTER  ---- final method
                adapterCity.clear();
                callApi();
                adapterCity.addAll(Tools.cityName);
                adapterCity.notifyDataSetChanged();
                autocompleteCity.setAdapter(adapterCity);
            }
        });

        autocompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                findCity = autocompleteCity.getText().toString();
                Tools.trueSearch = false;
                Tools.foundCountry = true;
                Tools.foundCity = true;
                Tools.foundNum = false;
                autocompleteAddr.setText("");
                //SISTEMARE ADAPTER  ---- final method
                adapterAddr.clear();
                callApi();
                adapterAddr.addAll(Tools.addressName);
                adapterAddr.notifyDataSetChanged();
                autocompleteAddr.setAdapter(adapterAddr);
            }
        });

        autocompleteAddr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                findAddr = autocompleteAddr.getText().toString();
                Tools.trueSearch = false;
                Tools.foundCountry = true;
                Tools.foundCity = true;
                Tools.foundNum = false;
                Tools.foundAddr = true;
                autocompleteNum.setText("");
                //SISTEMARE ADAPTER  ---- final method
                adapterNum.clear();
                Tools.listNum.clear();
                callApi();
                adapterNum.addAll(Tools.listNum);
                adapterNum.notifyDataSetChanged();
                autocompleteNum.setAdapter(adapterNum);
            }
        });

        autocompleteNum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                findNumHouse = autocompleteNum.getText().toString();
                Tools.trueSearch = false;
                Tools.foundCountry = true;
                Tools.foundCity = true;
                Tools.foundAddr = true;
                Tools.foundNum = true;
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Log.d("FINDWHO", findCity);
                findNumHouse = autocompleteNum.getText().toString();
                findCity = autocompleteCity.getText().toString();
                findAddr = autocompleteAddr.getText().toString();
                findCountry = autocomplete.getText().toString();
                Tools.trueSearch = true;
                Tools.errorShw = true;
                if (findCountry.equalsIgnoreCase("")) {
                    errorMsg2();
                    Tools.trueSearch = false;
                } else if (findCity.equalsIgnoreCase("")) {
                    errorMsg2();
                    Tools.trueSearch = false;
                } else if (findAddr.equalsIgnoreCase("")) {
                    errorMsg2();
                    Tools.trueSearch = false;
                } else if (findNumHouse.equalsIgnoreCase("")) {
                    errorMsg2();
                    Tools.trueSearch = false;
                }
                Tools.foundCountry = true;
                Tools.foundCity = true;
                Tools.foundAddr = true;
                Tools.foundNum = true;
                if (Tools.trueSearch == true){
                    callApi();
                }
            }
        });
    }


    void initUI(){
        findBtn = (FloatingActionButton) findViewById(R.id.findBtn);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        autocompleteCity = (AutoCompleteTextView) findViewById(R.id.autocompleteCity);
        autocompleteAddr = (AutoCompleteTextView) findViewById(R.id.autocompleteAddr);
        autocompleteNum = (AutoCompleteTextView) findViewById(R.id.autocompleteNum);
    }

    void callApi(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this); //Singleton ->
        String url = Tools.URLMAP; //Assigning from static class?
        Tools.errorShw = true; //Assigning from static class?

        // Request a string response from the provided URL.
        //This should a JSONObject

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            //create first jSON object -> key: province, value: Array of objects.
                            JSONObject province = new JSONObject(response);


                            //create array of objects by value of "province".
                            JSONArray jsonArray = new JSONArray(province.optString("Province"));

                            //parse the array of coutries
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                int id = jsonobject.getInt("CodiceIstatProvincia");
                                String country = jsonobject.getString("Provincia");
                                String url = jsonobject.getString("url");

                                //for each object, dive in its link and find all the cities.

                                //callApiCity(url);
                                if (Tools.foundCountry == false)
                                {
                                    Tools.countryName.add(country);
                                }
                                else {
                                    if (country.equalsIgnoreCase(findCountry.replaceAll("\\s+$", ""))){
                                        Log.d("SYSTEM", "FOUND COUNTRY:" + country);
                                        Tools.errorShw = false;
                                        callApiCity(url);
                                        break;
                                    }
                                }
                            }
                           if (Tools.trueSearch == true && Tools.errorShw == true){
                                Log.d("MISSING", "COUNTRY");
                                errorMsg();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"-1",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



    //find all cities by each country
    void callApiCity(String urlCity){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlCity;

        Log.d("SYSTEM", "CALL APIcity");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            //create first jSON object -> key: province, value: Array of objects.
                            JSONObject provincia = new JSONObject(response);


                            //create object of city by value of "provincia".
                            JSONObject jsonObject = new JSONObject(provincia.optString("Provincia"));


                            //pull the array of cities
                            JSONArray jsonArray = new JSONArray(jsonObject.optString("Comuni"));
                            Tools.errorShw = true;

                            //parse the array of coutries
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                int id = jsonobject.getInt("CodiceIstatComune");
                                String city = jsonobject.getString("Comune");
                                String url = jsonobject.getString("url");


                                if(Tools.foundCity == true) {
                                    if (city.equalsIgnoreCase(findCity.replaceAll("\\s+$", ""))) {
                                        Log.d("SYSTEM", "FOUND CITY:" + city);
                                        Tools.errorShw = false;
                                        callApiAddr(url);
                                        break;
                                    }
                                } else {
                                    Tools.cityName.add(city);
                                }
                            }
                            if (Tools.trueSearch == true && Tools.errorShw == true){
                                Log.d("MISSING", "CITY");
                                errorMsg();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        Tools.foundCity = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"-1",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //find addresses by city
    void callApiAddr(String urlAddr){
        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlAddr;
        Log.d("SYSTEM", "CALL APIADDR");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //create first jSON object -> key: province, value: Array of objects.
                            JSONObject provincia = new JSONObject(response);


                            //create object of city by value of "provincia".
                            JSONObject jsonObject = new JSONObject(provincia.optString("Provincia"));


                            //pull the array of cities
                            JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Comune"));

                            JSONArray jsonArray = new JSONArray(jsonObject1.optString("Strade"));

                            Tools.errorShw = true;
                            //parse the array of coutries
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                String address = jsonobject.getString("Strada");
                                String url = jsonobject.getString("url");

                                if(Tools.foundAddr == true) {
                                    if (address.equalsIgnoreCase(findAddr.replaceAll("\\s+$", ""))) {  //regex to remove whitespace
                                        Log.d("SYSTEM", "FOUND ADDR:" + address);
                                        Tools.errorShw = false;
                                        callApiNum(url);
                                        break;
                                    }
                                } else {
                                    Tools.addressName.add(address);

                                }
                            }
                            if (Tools.trueSearch == true && Tools.errorShw == true){
                                Log.d("MISSING", "ADDR");
                                errorMsg();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"-1",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //find all address num
    void callApiNum(String urlNum){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlNum;

        Log.d("SYSTEM", "CALL APINUM");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            //create first jSON object -> key: province, value: Array of objects.
                            JSONObject provincia = new JSONObject(response);


                            //create object of city by value of "provincia".
                            JSONObject jsonObject = new JSONObject(provincia.optString("Provincia"));


                            //pull the array of cities
                            JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Comune"));

                            JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("Strada"));

                            JSONArray jsonArray = new JSONArray(jsonObject2.optString("Civici"));

                            Tools.errorShw = true;
                            //parse the array of coutries
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonobject = jsonArray.getJSONObject(i);

                                String num = jsonobject.getString("Civico");
                                String url = jsonobject.getString("url");

                                if(Tools.foundNum == true) {
                                    if (num.equalsIgnoreCase(findNumHouse)) {
                                        Log.d("SYSTEM", "FOUND NUM:" + num);
                                        Tools.errorShw = false;
                                        Toast.makeText(getApplicationContext(), "FOUND ON DATABASE", Toast.LENGTH_LONG).show();
                                        reqApi(url);
                                        break;
                                    }
                                } else {
                                Tools.listNum.add(num);

                                }
                            }
                            if (Tools.trueSearch == true && Tools.errorShw == true){
                                Log.d("MISSING", "NUM");
                                errorMsg();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"-1",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //all details
    void reqApi (String urlApi){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlApi;
        Log.d("SYSTEM", "CALL API");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            //create first jSON object -> key: province, value: Array of objects.
                            JSONObject provincia = new JSONObject(response);


                            //create object of city by value of "provincia".
                            JSONObject jsonObject = new JSONObject(provincia.optString("Provincia"));


                            //pull the array of cities
                            JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Comune"));

                            JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("Strada"));

                            JSONArray jsonArray = new JSONArray(jsonObject2.optString("Civico"));

                            JSONObject jsonObject3 = jsonArray.getJSONObject(0);

                            //prepare new obj for intent
                            ShapeLine objDetail = new ShapeLine();
                            objDetail.setAddress(findCity+","+findAddr+ " " + findNumHouse);
                            objDetail.setAdsl_Dw(jsonObject3.getString("VELOCITA_DOWN_12_dB_BITSTREAM_ASIM_ETHERNET"));
                            objDetail.setAdsl_Up(jsonObject3.getString("VELOCITA_UP_12dB_BITSTREAM_ASIM_ETHERNET"));
                            objDetail.setDist_cab(jsonObject3.getString("DISTANZA_BITSTREAM_NGA_VULA_FTTCab"));
                            objDetail.setFttc(jsonObject3.getString("EDR_SLU"));
                            objDetail.setVdslStatus(jsonObject3.getString("COPERTURA_EVDSL_BITSTREAM_NGA_VULA_FTTCab"));


                            objDetail.setVdsl_Dw(jsonObject3.getString("VELOCITA_DOWN_EVDSL_BITSTREAM_NGA_VULA_FTTCab"));
                            objDetail.setVdsl_Up(jsonObject3.getString("VELOCITA_UP_EVDSL_BITSTREAM_NGA_VULA_FTTCab"));

                            getResult(objDetail);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"-1",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    //intent to start another activity --> result
    public void getResult(ShapeLine shapeLine) {
        Intent intent = new Intent(this, ResultActivity.class);
        Gson gson = new Gson();
        String shapeLineString = gson.toJson(shapeLine);
        intent.putExtra("shapeLineObj", shapeLineString);
        startActivity(intent);
    }

    public void errorMsg (){
        Toast.makeText(getApplicationContext(),"Dati non presenti o errati",Toast.LENGTH_LONG).show();
        Tools.trueSearch = false;
        Tools.errorShw = true;
    }
    public void errorMsg2 (){
        Toast.makeText(getApplicationContext(),"Inserire i dati prima di proseguire",Toast.LENGTH_LONG).show();
        Tools.trueSearch = false;
    }



}
