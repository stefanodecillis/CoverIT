package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {


    private InfoLine shapeObj = null;
    private Gson gson = null;

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
        } else if (Utils.infoLine.getVdslStatus().equalsIgnoreCase("-")){
            vdslView.setImageResource(R.drawable.ic_yellow);
        } else {
            vdslView.setImageResource(R.drawable.ic_red);
        }
        if (Utils.infoLine.getSimm_eth_status().equalsIgnoreCase(Constants.active)){
            simmView.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getSimm_eth_status().equalsIgnoreCase(Constants.inactive)){
            simmView.setImageResource(R.drawable.ic_red);
        } else {
            simmView.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getNga_vula_Status().equalsIgnoreCase(Constants.active)){
            nga_vula_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getNga_vula_Status().equalsIgnoreCase(Constants.inactive)){
            nga_vula_img.setImageResource(R.drawable.ic_red);
        } else {
            nga_vula_img.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getAsim_atm_status().equalsIgnoreCase(Constants.active)){
            asim_atm_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getAsim_atm_status().equalsIgnoreCase(Constants.inactive)){
            asim_atm_img.setImageResource(R.drawable.ic_red);
        }  else {
            asim_atm_img.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.active)){
            asim_eth_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.inactive)){
            asim_eth_img.setImageResource(R.drawable.ic_red);
        } else {
            asim_eth_img.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getSimm_atm_status().equalsIgnoreCase(Constants.active)){
            simm_atm_imgg.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getAsim_eth_Status().equalsIgnoreCase(Constants.inactive)){
            simm_atm_imgg.setImageResource(R.drawable.ic_red);
        } else {
            simm_atm_imgg.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getUll_Status().equalsIgnoreCase(Constants.active)){
            ull_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getUll_Status().equalsIgnoreCase(Constants.inactive)){
            ull_img.setImageResource(R.drawable.ic_red);
        } else {
            ull_img.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.active)){
            term_eth_fibr_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.inactive)){
            term_eth_fibr_img.setImageResource(R.drawable.ic_red);
        } else {
            term_eth_fibr_img.setImageResource(R.drawable.ic_yellow);
        }
        if (Utils.infoLine.getSlu_status().equalsIgnoreCase("SI")){
            slu_img.setImageResource(R.drawable.ic_green);
        } else if (Utils.infoLine.getTerm_eth_fibr().equalsIgnoreCase(Constants.inactive)){
            slu_img.setImageResource(R.drawable.ic_red);
        } else {
            slu_img.setImageResource(R.drawable.ic_yellow);
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


    //return my view using Utils class by redrawing all the image
    @Override
    protected void onResume() {
        super.onResume();
        initUI();
    }

}
