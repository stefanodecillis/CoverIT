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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://marlin.planetel.it/netmap/";


    //objectUI
    private FloatingActionButton findBtn = null;
    private AutoCompleteTextView autocomplete = null;
    private AutoCompleteTextView autocompleteCity = null;
    private AutoCompleteTextView autocompleteAddr = null;
    private AutoCompleteTextView autocompleteNum = null;

    private Gson gson;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        //build gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        //fetch request
        requestQueue = Volley.newRequestQueue(this);
        fetch();
    }


    //ButterKnife should be used instead.
    void initUI(){
        findBtn = (FloatingActionButton) findViewById(R.id.findBtn);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        autocompleteCity = (AutoCompleteTextView) findViewById(R.id.autocompleteCity);
        autocompleteAddr = (AutoCompleteTextView) findViewById(R.id.autocompleteAddr);
        autocompleteNum = (AutoCompleteTextView) findViewById(R.id.autocompleteNum);
    }


    //intent to start another activity --> result
    public void getResult(ShapeLine shapeLine) {
        Intent intent = new Intent(this, ResultActivity.class);
        Gson gson = new Gson();
        String shapeLineString = gson.toJson(shapeLine);
        intent.putExtra("shapeLineObj", shapeLineString);
        startActivity(intent);
    }

    private void errorMsg (){
        Toast.makeText(getApplicationContext(),"Dati non presenti o errati",Toast.LENGTH_LONG).show();
        Tools.trueSearch = false;
        Tools.errorShw = true;
    }
    private void errorMsg2 (){
        Toast.makeText(getApplicationContext(),"Inserire i dati prima di proseguire",Toast.LENGTH_LONG).show();
        Tools.trueSearch = false;
    }

    private void fetch() {
        Log.i("FETCH", "start");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ENDPOINT,onPostsLoaded,onError);

        requestQueue.add(stringRequest);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Utils.arrayClassUtil = gson.fromJson(response, ArrayClassUtil.class);  //reflection per l'array di province --> ERROR

            Log.i("Prov_Activity", response);
        }
    };

    private final Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Prov_Activity", error.toString());
        }
    };

    //funzione di prova per vedere se reflection funziona
    private void printAll() {
        for (int i = 0; i < Utils.arrayClassUtil.Province.size(); i++){
            Log.d("PRINT", Utils.arrayClassUtil.Province.get(i).name);
        }
    }



}
