package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {


    private static final String CIAIA = "GGSGS" ;
    private InfoLine shapeObj = null;
    private Gson gson = null;
    private int[] covStat = null;
    private int[] covId = null;

    //UI objects
    @BindView(R.id.vdslView)
    ImageView vdslView = null;
    @BindView(R.id.simm_eth_img)
    ImageView simmView = null;
    @BindView(R.id.moreBtn)
    Button moreBtn = null;
    @BindView(R.id.nga_vula_img)
    ImageView nga_vula_img = null;
    @BindView(R.id.asim_atm_img)
    ImageView asim_atm_img = null;
    @BindView(R.id.asim_eth_img)
    ImageView asim_eth_img = null;
    @BindView(R.id.term_eth_fibr_img)
    ImageView term_eth_fibr_img = null;
    @BindView(R.id.ull_img)
    ImageView ull_img = null;
    @BindView(R.id.simm_atm_imgg)
    ImageView simm_atm_imgg = null;
    @BindView(R.id.slu_img)
    ImageView slu_img = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //init UI
        ButterKnife.bind(this);

        gson = new Gson();
        covStat = new int[9];
        covId = new int[9];


        String shapeLineAsString = getIntent().getStringExtra(Constants.intent_extra);
        if (shapeLineAsString != null) {
            shapeObj = gson.fromJson(shapeLineAsString, InfoLine.class);
            Utils.infoLine = shapeObj;   //i'm saving in memory in order to return in this activity with back button and restore values
        }

        if (shapeObj != null) {
           initUI();
        }

        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDetail(shapeObj);
            }
        });

    }


    private void initUI(){
        if (Utils.infoLine.getVdslStatus().equalsIgnoreCase(Constants.active)){
            vdslView.setImageResource(R.drawable.ic_green);
            covId[0] = vdslView.getId();
            covStat[0] = 1;
        } else if (Utils.infoLine.getVdslStatus().equalsIgnoreCase("-")){
            vdslView.setImageResource(R.drawable.ic_yellow);
            covId[0] = vdslView.getId();
            covStat[0] = 2;
        } else {
            vdslView.setImageResource(R.drawable.ic_red);
            covId[0] = vdslView.getId();
            covStat[0] = 3;
        }
        if (Utils.infoLine.getSimm_eth_status().equalsIgnoreCase(Constants.active)){
            simmView.setImageResource(R.drawable.ic_green);
            covId[1] = simmView.getId();
            covStat[1] = 1;
        } else if (Utils.infoLine.getSimm_eth_status().equalsIgnoreCase(Constants.inactive)){
            simmView.setImageResource(R.drawable.ic_red);
            covId[1] = simmView.getId();
            covStat[1] = 2;
        } else {
            simmView.setImageResource(R.drawable.ic_yellow);
            covId[1] = simmView.getId();
            covStat[1] = 3;
        }
        if (Utils.infoLine.getNga_vula_Status().equalsIgnoreCase(Constants.active)){
            nga_vula_img.setImageResource(R.drawable.ic_green);
            covId[2] = nga_vula_img.getId();
            covStat[2] = 1;
        } else if (Utils.infoLine.getNga_vula_Status().equalsIgnoreCase(Constants.inactive)){
            nga_vula_img.setImageResource(R.drawable.ic_red);
            covId[2] = nga_vula_img.getId();
            covStat[2] = 2;
        } else {
            nga_vula_img.setImageResource(R.drawable.ic_yellow);
            covId[2] = nga_vula_img.getId();
            covStat[2] = 3;
        }
        if (Utils.infoLine.getAsim_atm_status().equalsIgnoreCase(Constants.active)){
            asim_atm_img.setImageResource(R.drawable.ic_green);
            covId[3] = asim_atm_img.getId();
            covStat[3] = 1;
        } else if (Utils.infoLine.getAsim_atm_status().equalsIgnoreCase(Constants.inactive)){
            asim_atm_img.setImageResource(R.drawable.ic_red);
            covId[3] = asim_atm_img.getId();
            covStat[3] = 2;
        }  else {
            asim_atm_img.setImageResource(R.drawable.ic_yellow);
            covId[3] = asim_atm_img.getId();
            covStat[3] = 3;
        }
        if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.active)){
            asim_eth_img.setImageResource(R.drawable.ic_green);
            covId[4] = asim_eth_img.getId();
            covStat[4] = 1;
        } else if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.inactive)){
            asim_eth_img.setImageResource(R.drawable.ic_red);
            covId[4] = asim_eth_img.getId();
            covStat[4] = 2;
        } else {
            asim_eth_img.setImageResource(R.drawable.ic_yellow);
            covId[4] = asim_eth_img.getId();
            covStat[4] = 3;
        }
        if (Utils.infoLine.getSimm_atm_status().equalsIgnoreCase(Constants.active)){
            simm_atm_imgg.setImageResource(R.drawable.ic_green);
            covId[5] = simm_atm_imgg.getId();
            covStat[5] = 1;
        } else if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.inactive)){
            simm_atm_imgg.setImageResource(R.drawable.ic_red);
            covId[5] = simm_atm_imgg.getId();
            covStat[5] = 2;
        } else {
            simm_atm_imgg.setImageResource(R.drawable.ic_yellow);
            covId[5] = simm_atm_imgg.getId();
            covStat[5] = 3;
        }
        if (Utils.infoLine.getUll_Status().equalsIgnoreCase(Constants.active)){
            ull_img.setImageResource(R.drawable.ic_green);
            covId[6] = ull_img.getId();
            covStat[6] = 1;
        } else if (Utils.infoLine.getUll_Status().equalsIgnoreCase(Constants.inactive)){
            ull_img.setImageResource(R.drawable.ic_red);
            covId[6] = ull_img.getId();
            covStat[6] = 2;
        } else {
            ull_img.setImageResource(R.drawable.ic_yellow);
            covId[6] = ull_img.getId();
            covStat[6] = 3;
        }
        if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.active)){
            term_eth_fibr_img.setImageResource(R.drawable.ic_green);
            covId[7] = term_eth_fibr_img.getId();
            covStat[7] = 1;
        } else if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.inactive)){
            term_eth_fibr_img.setImageResource(R.drawable.ic_red);
            covId[7] = term_eth_fibr_img.getId();
            covStat[7] = 2;
        } else {
            term_eth_fibr_img.setImageResource(R.drawable.ic_yellow);
            covId[7] = term_eth_fibr_img.getId();
            covStat[7] = 3;
        }
        if (Utils.infoLine.getSlu_status().equalsIgnoreCase("SI")){
            slu_img.setImageResource(R.drawable.ic_green);
            covId[8] = slu_img.getId();
            covStat[8]= 1;
        } else if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.inactive)){
            slu_img.setImageResource(R.drawable.ic_red);
            covId[8] = slu_img.getId();
            covStat[8]= 2;
        } else {
            slu_img.setImageResource(R.drawable.ic_yellow);
            covId[8] = slu_img.getId();
            covStat[8]= 3;
        }
    }

    //intent to start another activity --> detail
    public void getDetail(InfoLine infoLine) {
        Intent intent = new Intent(this, DetailActivity.class);
        Gson gson = new Gson();
        String shapeLineString = gson.toJson(infoLine);
        intent.putExtra(Constants.intent_extra, shapeLineString);
        startActivity(intent);
    }

/*
    //return my view using Utils class by redrawing all the image
    @Override
    protected void onResume() {
        super.onResume();
        initUI();
    }
*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntArray(Constants.STATE_VIEW,covStat);
        outState.putIntArray(Constants.ID_VIEW,covId);

        Log.d("ERRLOAD", "Err load result layout");
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("RESTORE", "restoring data");
        if(savedInstanceState != null){
            int[] states = (int[]) savedInstanceState.get(Constants.STATE_VIEW);
            int[] id_view = (int[]) savedInstanceState.get(Constants.ID_VIEW);
            for (int i = 0; i < states.length; i++){
                ImageView imageView = (ImageView) findViewById(id_view[i]);
                if (states[i] == 1){
                    imageView.setImageResource(R.drawable.ic_green);
                } else if (states[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_red);
                } else if (states[i] == 3) {
                    imageView.setImageResource(R.drawable.ic_yellow);
                } else {
                    Log.d("ERRLOAD", "Err load result layout");
                }
            }
        }
    }


    private void restoreUI(Bundle bundle) {
        int[] states = (int[]) bundle.get(Constants.STATE_VIEW);
        int[] id_view = (int[]) bundle.get(Constants.ID_VIEW);
        for (int i = 0; i < states.length; i++){
            ImageView imageView = (ImageView) findViewById(id_view[i]);
            if (states[i] == 1){
                imageView.setImageResource(R.drawable.ic_green);
            } else if (states[i] == 2) {
                imageView.setImageResource(R.drawable.ic_red);
            } else if (states[i] == 3) {
                imageView.setImageResource(R.drawable.ic_yellow);
            } else {
                Log.d("ERRLOAD", "Err load result layout");
            }
        }
    }
}
