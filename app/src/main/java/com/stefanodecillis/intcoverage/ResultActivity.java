package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    private InfoLine shapeObj = null;
    private Gson gson = null;
    private String object_json = null;


    //UI objects
    @BindView(R.id.moreBtn)
    Button moreBtn = null;
    @BindView(R.id.list)
    ListView listView = null;
    @BindView(R.id.my_toolbar)
    android.support.v7.widget.Toolbar myToolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //init UI
        ButterKnife.bind(this);
        getSupportToolbar();

        gson = new Gson();
        List<ListItem> list = new LinkedList<ListItem>();

        String shapeLineAsString;

        if(savedInstanceState != null){
            Log.d("Bundle", "restoring instance in onCreate()"); //debug
            shapeLineAsString = savedInstanceState.getString(Constants.JSON_RESULT); //string value from saved instance
       } else {
            shapeLineAsString = getIntent().getStringExtra(Constants.intent_extra); //first time create view, it got intent
       }


       /*
       I'm retrieving data from reflection to set my UI
        */
        if (shapeLineAsString != null) {
            object_json = shapeLineAsString;
            shapeObj = gson.fromJson(shapeLineAsString, InfoLine.class);
        }

        if (shapeObj != null) {
            initUI(list);
        }

        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDetail(shapeObj);
            }
        });

    }


    private void initUI(List<ListItem> list){
        list.add(new ListItem(Constants.SLU,shapeObj.getSlu_status()));
        list.add(new ListItem(Constants.NGA_VULA, shapeObj.getNga_vula_Status()));
        list.add(new ListItem(Constants.VDSL_NGA_VULA,shapeObj.getVdslStatus()));
        list.add(new ListItem(Constants.ASIM_ATM,shapeObj.getAsim_atm_status()));
        list.add(new ListItem(Constants.ASIM_ETH,shapeObj.getAsim_eth_Status()));
        list.add(new ListItem(Constants.SIMM_ATM,shapeObj.getSimm_atm_status()));
        list.add(new ListItem(Constants.SIMM_ETH,shapeObj.getSimm_eth_status()));
        list.add(new ListItem(Constants.ULL,shapeObj.getUll_Status()));
        list.add(new ListItem(Constants.WLR_STATUS,shapeObj.getWlr_status()));
        list.add(new ListItem(Constants.TERM_ETH_FIBRA,shapeObj.getTerm_eth_fibr()));

        //set adapter
        CustomAdapter adapter = new CustomAdapter(this, R.layout.listitem, list);
        listView.setAdapter(adapter);
    }

    //intent to start another activity --> detail
    public void getDetail(InfoLine infoLine) {
        Intent intent = new Intent(this, DetailActivity.class);
        Gson gson = new Gson();
        String shapeLineString = gson.toJson(infoLine);
        intent.putExtra(Constants.intent_extra, shapeLineString);
        startActivity(intent);
    }

    private void getSupportToolbar(){
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        //saving json string on bundle
        outState.putString(Constants.JSON_RESULT,object_json);

        /*debug*/
        Log.d("Bundle", "saved instance");

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

}
