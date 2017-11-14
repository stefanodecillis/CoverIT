package com.stefanodecillis.intcoverage;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stefanodecillis.intcoverage.Entities.ArrayClassUtil;
import com.stefanodecillis.intcoverage.Entities.Città;
import com.stefanodecillis.intcoverage.Entities.Civico;
import com.stefanodecillis.intcoverage.Entities.InfoLine;
import com.stefanodecillis.intcoverage.Entities.Provincia;
import com.stefanodecillis.intcoverage.Entities.Strada;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //objectUI
    @BindView(R.id.findBtn)
    FloatingActionButton findBtn = null;
    @BindView(R.id.autocomplete)
    AutoCompleteTextView autocomplete = null;
    @BindView(R.id.autocompleteCity)
    AutoCompleteTextView autocompleteCity = null;
    @BindView(R.id.autocompleteAddr)
    AutoCompleteTextView autocompleteAddr = null;
    @BindView(R.id.autocompleteNum)
    AutoCompleteTextView autocompleteNum = null;

    private Gson gson;
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog = null;
    private ArrayClassUtil arrayClassUtil;
    private InfoLine infoLine;
    private ArrayList<Provincia> province;
    private ArrayList<Città> comuni;
    private ArrayList<Strada> strade;
    private ArrayList<Civico> civici;
    private String findCountry = "";
    private String findCity = "";
    private String findAddr = "";
    private String findNumHouse = "";
    private ArrayList<String> provList = new ArrayList<String>();
    private ArrayList<String> comList = new ArrayList<String>();
    private ArrayList<String> addrList = new ArrayList<String>();
    private ArrayList<String> numList = new ArrayList<String>();
    private ArrayAdapter<String> provAdapter;
    private ArrayAdapter<String> comAdapter;
    private ArrayAdapter<String> addrAdapter;
    private ArrayAdapter<String> numAdapter;
    private Boolean search = false;
    private Boolean searchAddr = false;

    //bool to click just once findBtn
    private Boolean findBtnOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Butterknife is used here
        ButterKnife.bind(this);
        initUI();

        //init adapters
        provAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, provList);
        comAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, comList);
        addrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, addrList);
        numAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, numList);

        //build gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        //fetch request
        requestQueue = Volley.newRequestQueue(this);
        if(provList.isEmpty() || provList == null) {
            fetchProv();  //prevent from adding names going back to this view
        } else {
            autocomplete.setAdapter(provAdapter);
        }

    }


    private void initUI() {

        autocompleteNum.setEnabled(false);
        autocompleteAddr.setEnabled(false);
        autocompleteCity.setEnabled(false);
        findBtn.setEnabled(false);

        //color button
        PorterDuffColorFilter redFilter = new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        findBtn.getBackground().setColorFilter(redFilter);

        //items on autocompleteTextView clicked
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Aggiorno comuni..");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                onProvClicked(adapter,view,pos,id);

                search = false;
                searchAddr = false;
                autocompleteAddr.setEnabled(true);
                //autocompleteAddr.setClickable(false);
            }
        });


        autocompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Aggiorno indirizzi.. (potrebbe volerci qualche secondo)");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                onComClicked(adapter,view,pos,id);

                //autocompleteNum.setEnabled(true);
            }
        });

        autocompleteAddr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Aggiorno numeri civici..");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();

                onAddrClicked(adapter,view,pos,id);

                findBtn.setEnabled(true);
                findBtn.getBackground().setColorFilter(null);
            }
        });

        //if someone does not click some items, I need to fetch new data wheh user touches next autocomplete
        autocompleteAddr.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (autocompleteCity.getText().toString().isEmpty()){

                    /*
                     * do nothing
                     */
                    return true;
                } else {


                    if (searchAddr == false) {
                        if (comuni != null) {
                            for (int i = 0; i < comuni.size(); i++) {
                                if (autocompleteCity.getText().toString().equalsIgnoreCase(comuni.get(i).getName())) {
                                    Log.d("COM_ADDED", autocompleteCity.getText().toString());

                                    progressDialog = new ProgressDialog(MainActivity.this);
                                    progressDialog.setMessage("Aggiorno indirizzi.. (potrebbe volerci qualche secondo)");
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setCancelable(false);
                                    progressDialog.show();

                                    autocompleteAddr.setClickable(true);
                                    autocompleteNum.setEnabled(true);

                                    autocompleteAddr.setText("");

                                    findCity = autocompleteCity.getText().toString();


                                    fetchAddr(comuni.get(i).getUrl());
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Scegli prima la città desiderata", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return false;
            }
        });

        //if someone does not click some items, I need to fetch new data wheh user touches next autocomplete
        autocompleteNum.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (autocompleteAddr.getText().toString().isEmpty()){

                    /*
                     * do nothing
                     */
                    return true;
                } else {

                    autocompleteNum.setEnabled(true);

                    if (search == false) {
                        if(strade != null) {
                            for (int i = 0; i < strade.size(); i++) {
                                if (autocompleteAddr.getText().toString().equalsIgnoreCase(strade.get(i).getName())) {
                                    Log.d("ADDR_ADDED", autocompleteAddr.getText().toString());
                                    findAddr = autocompleteAddr.getText().toString();

                                    progressDialog = new ProgressDialog(MainActivity.this);
                                    progressDialog.setMessage("Aggiorno numeri civici..");
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setCancelable(false);
                                    progressDialog.show();


                                    fetchNum(strade.get(i).getUrl());

                                    findBtn.setEnabled(true);
                                    findBtn.getBackground().setColorFilter(null);
                                    return true;
                                }
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Scegli prima l'indirizzo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return false;
            }
        });

        //floating button clicked
        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (findBtnOpen == false) {
                    findBtnOpen = true;
                    if (checkAutocompleteTxt() && checkLists()) {                      //check if i'm using null arrayList
                        findNumHouse = autocompleteNum.getText().toString();
                        boolean found = false;
                        if (findNumHouse != "" || findNumHouse != null) {          //check if i'm searching some null value
                            for (int i = 0; i < civici.size(); i++) {
                                if (findNumHouse.equalsIgnoreCase(civici.get(i).getCivico())) {

                                    progressDialog = new ProgressDialog(MainActivity.this);
                                    progressDialog.setMessage("Scarico i dati richiesti");
                                    progressDialog.setCancelable(false);
                                    progressDialog.show();

                                    //set bool true
                                    found = true;

                                /* debug */
                                    Log.d(Constants.fetching, "Fetching");

                                    //fetching data
                                    fetchInfo(civici.get(i).getUrl());
                                }
                            }
                            if (found == false) {
                                Toast.makeText(getApplicationContext(), "Civico non trovato", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Some field is missed or not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), Constants.err_fields, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //intent to start another activity --> result
    public void getResult(InfoLine infoLine) {
        Intent intent = new Intent(this, ResultActivity.class);
        Gson gson = new Gson();
        String shapeLineString = gson.toJson(infoLine);
        intent.putExtra(Constants.intent_extra, shapeLineString);

        //finished to fetch data and fill adapter
        progressDialog.hide();
        progressDialog.dismiss();
        findBtnOpen = false;

        startActivity(intent);
    }

    /*
    a few methods to check null pointer
     */


    private Boolean checkAutocompleteTxt(){
        if (autocomplete.getText().toString() != "" &&
                autocompleteCity.getText().toString() != "" &&
                autocompleteAddr.getText().toString() != "" &&
                autocompleteNum.getText().toString() != ""){
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkLists(){
        if (arrayClassUtil != null){
            if (arrayClassUtil.getProvince() != null &&
                    arrayClassUtil.getComuni() != null &&
                    arrayClassUtil.getStrade() != null &&
                    arrayClassUtil.getCivici() != null ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //methods to fetch data
    private void fetchProv() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URLMAP,onPostsLoaded,onError);
        getRetryPolicy(stringRequest);
        requestQueue.add(stringRequest);
    }
    private void fetchCom(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onCitiesLoaded,onError);

        getRetryPolicy(request);
        requestQueue.add(request);
    }
    private void fetchAddr(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onAddrLoaded,onError);

        getRetryPolicy(request);
        searchAddr = true;

        requestQueue.add(request);
    }
    private void fetchNum(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onNumLoaded,onError);

        search = true;
        getRetryPolicy(request);
        requestQueue.add(request);
    }
    private boolean fetchInfo(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onPostLoaded,onError);
        getRetryPolicy(request);
        requestQueue.add(request);
        return true;
    }

    //methods for onReponse --> each method pulls different data
    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            arrayClassUtil = gson.fromJson(response, ArrayClassUtil.class);  //reflection per l'array
            province = arrayClassUtil.getProvince();
            fillAdapter();
            Log.i("Prov_Activity", response);
        }
    };
    private final Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(Constants.err_fetch, error.toString());
            findBtnOpen = false;
        }
    };

    private final Response.Listener<String> onCitiesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Provincia"));
                for (int i = 0; i < arrayClassUtil.getProvince().size(); i++){
                    if (findCountry.equalsIgnoreCase(arrayClassUtil.getProvince().get(i).getName())){
                        Provincia provincia = arrayClassUtil.getProvince().get(i);
                        provincia = gson.fromJson(String.valueOf(jsonObject1), Provincia.class);
                        arrayClassUtil.setComuni(provincia.getComuni());
                    }
                }
                comuni = arrayClassUtil.getComuni();
                Log.i("Com_Activity", response);
                if (comuni != null)
                    fillComAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
                progressDialog.dismiss();
                autocompleteCity.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private final Response.Listener<String> onAddrLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Provincia"));
                JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("Comune"));
                for (int i = 0; i < arrayClassUtil.getComuni().size(); i++){
                    if (findCity.equalsIgnoreCase(arrayClassUtil.getComuni().get(i).getName())){
                        Città citta = arrayClassUtil.getComuni().get(i);
                        citta = gson.fromJson(String.valueOf(jsonObject2), Città.class);
                        arrayClassUtil.setStrade(citta.getStrade());
                    }
                }
                strade = arrayClassUtil.getStrade();
                Log.i("Addr_Activity", response);
                if (strade != null)
                    fillAddrAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
                progressDialog.dismiss();
                autocompleteAddr.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private final Response.Listener<String> onNumLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Provincia"));
                JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("Comune"));
                for (int i = 0; i < arrayClassUtil.getComuni().size(); i++){
                    if (findCity.equalsIgnoreCase(arrayClassUtil.getComuni().get(i).getName())){
                        Città citta = arrayClassUtil.getComuni().get(i);
                        citta = gson.fromJson(String.valueOf(jsonObject2), Città.class);
                        arrayClassUtil.setCivici(citta.getStrada().getCivici());
                    }
                }
                civici = arrayClassUtil.getCivici();
                Log.i("Num_Activity", response);
                if (civici != null)
                    fillNumAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
                progressDialog.dismiss();
                autocompleteNum.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private final Response.Listener<String> onPostLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i(Constants.fetching, "start");
            try {
                //Api forces me to parse like this
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = new JSONObject(jsonObject.optString("Provincia"));
                JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("Comune"));
                JSONObject jsonObject3 = new JSONObject(jsonObject2.optString("Strada"));
                JSONArray jsonArray = new JSONArray(jsonObject3.optString("Civico"));

                //reflection for all info
                infoLine = gson.fromJson(String.valueOf(jsonArray.get(0)), InfoLine.class);

                Log.i("Post_Activity", response);

                getResult(infoLine);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


   //filling adapters
    private void fillAdapter() {
        for (int i = 0; i < province.size(); i++){
            provList.add(province.get(i).getName());
        }
        autocomplete.setAdapter(provAdapter);
    }

    private void fillComAdapter(){
        comAdapter.clear();
        comList.clear();
        for (int i = 0; i < comuni.size(); i++){
            comAdapter.add(comuni.get(i).getName());  //error-> adapting data for UI --> wrong
        }
        autocompleteCity.setAdapter(comAdapter);
    }
    private void fillAddrAdapter(){
        addrAdapter.clear();
        addrList.clear();
        for (int i = 0; i < strade.size(); i++){
           addrAdapter.add(strade.get(i).getName());
        }
        autocompleteAddr.setAdapter(addrAdapter);
    }

    private void fillNumAdapter(){
        numAdapter.clear();
        numList.clear();
        for (int i = 0; i < civici.size(); i++){
            numAdapter.add(civici.get(i).getCivico());
        }
        autocompleteNum.setAdapter(numAdapter);
    }

    //on AutocompleteTextView Clicked methods
    private void onProvClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < province.size(); i++) {
            if (autocomplete.getText().toString().equalsIgnoreCase(province.get(i).getName())) {
                Log.d("PROV_CLICKED", autocomplete.getText().toString());
                findCountry = autocomplete.getText().toString();
                fetchCom(province.get(i).getUrl());
                autocompleteCity.setText("");
            }
        }
    }

    private void onComClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < comuni.size(); i++) {
            if (autocompleteCity.getText().toString().equalsIgnoreCase(comuni.get(i).getName())) {
                Log.d("COM_CLICKED", autocompleteCity.getText().toString());
                findCity = autocompleteCity.getText().toString();
                fetchAddr(comuni.get(i).getUrl());
                autocompleteAddr.setText("");
            }
        }
    }

    private void onAddrClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < strade.size(); i++) {
            if (autocompleteAddr.getText().toString().equalsIgnoreCase(strade.get(i).getName())) {
                Log.d("ADDR_CLICKED", autocompleteAddr.getText().toString());
                findAddr = autocompleteAddr.getText().toString();
                fetchNum(strade.get(i).getUrl());
                autocompleteNum.setText("");
            }
        }
    }

    private void getRetryPolicy(StringRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
