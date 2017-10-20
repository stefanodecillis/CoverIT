package com.stefanodecillis.intcoverage;

import android.app.DownloadManager;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://marlin.planetel.it/netmap/";


    //objectUI
    @BindView(R.id.findBtn) FloatingActionButton findBtn = null;
    @BindView(R.id.autocomplete) AutoCompleteTextView autocomplete = null;
    @BindView(R.id.autocompleteCity) AutoCompleteTextView autocompleteCity = null;
    @BindView(R.id.autocompleteAddr) AutoCompleteTextView autocompleteAddr = null;
    @BindView(R.id.autocompleteNum) AutoCompleteTextView autocompleteNum = null;

    private Gson gson;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Butterknife is used here
        ButterKnife.bind(this);

        //init adapters
        Utils.provAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.provList);
        Utils.comAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.comList);

        //build gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        //fetch request
        requestQueue = Volley.newRequestQueue(this);
        fetchProv();

        //items on autocompleteTextView clicked
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                onProvClicked(adapter,view,pos,id);
            }
        });

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

    private void fetchProv() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ENDPOINT,onPostsLoaded,onError);

        requestQueue.add(stringRequest);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Utils.arrayClassUtil = gson.fromJson(response, ArrayClassUtil.class);  //reflection per l'array
            Utils.province = Utils.arrayClassUtil.Province;

            fillAdapter();
            Log.i("Prov_Activity", response);
        }
    };

    private final Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Prov_Activity", error.toString());
        }
    };


    private void fillAdapter() {
        for (int i = 0; i < Utils.province.size(); i++){
            Utils.provList.add(Utils.province.get(i).name);
        }
        autocomplete.setAdapter(Utils.provAdapter);
    }

    private void fetchCom(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onCitiesLoaded,onCitiesError);

        requestQueue.add(request);
    }

    private final Response.Listener<String> onCitiesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("FETCH", "start");

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Provincia"));

                for (int i = 0; i < Utils.arrayClassUtil.Province.size(); i++){
                    if (Utils.findCountry.equalsIgnoreCase(Utils.arrayClassUtil.Province.get(i).name)){
                        Provincia provincia = Utils.arrayClassUtil.Province.get(i);
                        provincia = gson.fromJson(String.valueOf(jsonObject1), Provincia.class);
                        Utils.arrayClassUtil.Comuni = provincia.Comuni;
                    }
                }
                Utils.comuni = Utils.arrayClassUtil.Comuni;

                Log.i("Prov_Activity", response);

                if (Utils.comuni != null)
                fillComAdapter();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private final Response.ErrorListener onCitiesError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Prov_Activity", error.toString());
        }
    };

    private void fillComAdapter(){
        for (int i = 0; i < Utils.comuni.size(); i++){
            Utils.comList.add(Utils.comuni.get(i).name);
        }
        Utils.comAdapter.notifyDataSetChanged();
        autocompleteCity.setAdapter(Utils.comAdapter);
    }


    private void onProvClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < Utils.province.size(); i++) {
            if (autocomplete.getText().toString().equalsIgnoreCase(Utils.province.get(i).name)) {
                Log.d("PROVCLICKED", autocomplete.getText().toString());
                Utils.findCountry = autocomplete.getText().toString();
                fetchCom(Utils.province.get(i).url);
            }
        }
    }

}
