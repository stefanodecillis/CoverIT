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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Butterknife is used here
        ButterKnife.bind(this);
        initUI();

        //init adapters
        Utils.provAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.provList);
        Utils.comAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.comList);
        Utils.addrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.addrList);
        Utils.numAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Utils.numList);

        //build gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        //fetch request
        requestQueue = Volley.newRequestQueue(this);
        if(Utils.provList.isEmpty() || Utils.provList == null) {
            fetchProv();  //prevent from adding names going back to this view
        } else {
            autocomplete.setAdapter(Utils.provAdapter);
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
            }
        });


        autocompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Aggiorno indirizzi..");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                onComClicked(adapter,view,pos,id);
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


        //floating button clicked
        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkAutocompleteTxt() && checkLists()) {                      //check if i'm using null arrayList
                    Utils.findNumHouse = autocompleteNum.getText().toString();
                    if (Utils.findNumHouse != "" || Utils.findNumHouse != null) {          //check if i'm searching some null value
                        for (int i = 0; i < Utils.civici.size(); i++) {
                            if (Utils.findNumHouse.equalsIgnoreCase(Utils.civici.get(i).civico)) {

                                progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setMessage("Scarico i dati richiesti");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                /* debug */
                                Log.d(Constants.fetching, "Fetching");

                                //fetching data
                                fetchInfo(Utils.civici.get(i).url);
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Some field is missed or not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),Constants.err_fields,Toast.LENGTH_SHORT).show();
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
        if (Utils.arrayClassUtil != null){
            if (Utils.arrayClassUtil.Province != null &&
                    Utils.arrayClassUtil.Comuni != null &&
                    Utils.arrayClassUtil.strade != null &&
                    Utils.arrayClassUtil.civici != null ) {
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
        requestQueue.add(stringRequest);
    }
    private void fetchCom(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onCitiesLoaded,onError);
        requestQueue.add(request);
    }
    private void fetchAddr(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onAddrLoaded,onError);
        requestQueue.add(request);
    }
    private void fetchNum(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onNumLoaded,onError);
        requestQueue.add(request);
    }
    private boolean fetchInfo(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url,onPostLoaded,onError);
        requestQueue.add(request);
        return true;
    }

    //methods for onReponse --> each method pulls different data
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
            Log.e(Constants.err_fetch, error.toString());
        }
    };

    private final Response.Listener<String> onCitiesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
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
                Log.i("Com_Activity", response);
                if (Utils.comuni != null)
                    fillComAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
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
                for (int i = 0; i < Utils.arrayClassUtil.Comuni.size(); i++){
                    if (Utils.findCity.equalsIgnoreCase(Utils.arrayClassUtil.Comuni.get(i).name)){
                        Città citta = Utils.arrayClassUtil.Comuni.get(i);
                        citta = gson.fromJson(String.valueOf(jsonObject2), Città.class);
                        Utils.arrayClassUtil.strade = citta.strade;
                    }
                }
                Utils.strade = Utils.arrayClassUtil.strade;
                Log.i("Addr_Activity", response);
                if (Utils.strade != null)
                    fillAddrAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
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
                for (int i = 0; i < Utils.arrayClassUtil.Comuni.size(); i++){
                    if (Utils.findCity.equalsIgnoreCase(Utils.arrayClassUtil.Comuni.get(i).name)){
                        Città citta = Utils.arrayClassUtil.Comuni.get(i);
                        citta = gson.fromJson(String.valueOf(jsonObject2), Città.class);
                        Utils.arrayClassUtil.civici = citta.strada.civici;
                    }
                }
                Utils.civici = Utils.arrayClassUtil.civici;
                Log.i("Num_Activity", response);
                if (Utils.civici != null)
                    fillNumAdapter();

                //finished to fetch data and fill adapter
                progressDialog.hide();
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
                Utils.infoLine = gson.fromJson(String.valueOf(jsonArray.get(0)), InfoLine.class);

                Log.i("Post_Activity", response);

                getResult(Utils.infoLine);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


   //filling adapters
    private void fillAdapter() {
        for (int i = 0; i < Utils.province.size(); i++){
            Utils.provList.add(Utils.province.get(i).name);
        }
        autocomplete.setAdapter(Utils.provAdapter);
    }

    private void fillComAdapter(){
        Utils.comAdapter.clear();
        Utils.comList.clear();
        for (int i = 0; i < Utils.comuni.size(); i++){
            Utils.comAdapter.add(Utils.comuni.get(i).name);  //error-> adapting data for UI --> wrong
        }
        autocompleteCity.setAdapter(Utils.comAdapter);
    }
    private void fillAddrAdapter(){
        Utils.addrAdapter.clear();
        Utils.addrList.clear();
        for (int i = 0; i < Utils.strade.size(); i++){
            Utils.addrAdapter.add(Utils.strade.get(i).name);
        }
        autocompleteAddr.setAdapter(Utils.addrAdapter);
    }

    private void fillNumAdapter(){
        Utils.numAdapter.clear();
        Utils.numList.clear();
        for (int i = 0; i < Utils.civici.size(); i++){
            Utils.numAdapter.add(Utils.civici.get(i).civico);
        }
        autocompleteNum.setAdapter(Utils.numAdapter);
    }

    //on AutocompleteTextView Clicked methods
    private void onProvClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < Utils.province.size(); i++) {
            if (autocomplete.getText().toString().equalsIgnoreCase(Utils.province.get(i).name)) {
                Log.d("PROV_CLICKED", autocomplete.getText().toString());
                Utils.findCountry = autocomplete.getText().toString();
                fetchCom(Utils.province.get(i).url);
                autocompleteCity.setText("");
            }
        }
    }

    private void onComClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < Utils.comuni.size(); i++) {
            if (autocompleteCity.getText().toString().equalsIgnoreCase(Utils.comuni.get(i).name)) {
                Log.d("COM_CLICKED", autocompleteCity.getText().toString());
                Utils.findCity = autocompleteCity.getText().toString();
                fetchAddr(Utils.comuni.get(i).url);
                autocompleteAddr.setText("");
            }
        }
    }

    private void onAddrClicked(AdapterView<?> adapter, View view, int pos,long id) {
        for (int i = 0; i < Utils.strade.size(); i++) {
            if (autocompleteAddr.getText().toString().equalsIgnoreCase(Utils.strade.get(i).name)) {
                Log.d("ADDR_CLICKED", autocompleteAddr.getText().toString());
                Utils.findAddr = autocompleteAddr.getText().toString();
                fetchNum(Utils.strade.get(i).url);
                autocompleteNum.setText("");
            }
        }
    }

}
