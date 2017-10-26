package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.addressTxt) TextView addressTxt = null;
    @BindView(R.id.fttcTxt) TextView fttcTxt = null;
    @BindView(R.id.distCabTxt) TextView distCabTxt = null;
    @BindView(R.id.adslDwTxt) TextView adslDwTxt = null;
    @BindView(R.id.adslUpTxt) TextView adslUpTxt = null;
    @BindView(R.id.vdslStatusTxt) TextView vdslStatusTxt = null;
    @BindView(R.id.vdslDwTxt) TextView vdslDwTxt = null;
    @BindView(R.id.vdslUpTxt) TextView vdslUpTxt = null;
    @BindView(R.id.sluStatusTxt) TextView sluStatusTxt = null;
    @BindView(R.id.sluDwTxt) TextView sluDwTxt = null;
    @BindView(R.id.sluUpTxt) TextView sluUpTxt = null;
    @BindView(R.id.asim_atm_StatusTxt) TextView asim_atm_statusTxt = null;
    @BindView(R.id.asim_atm_6dbDwTxt) TextView asim6db_dwTxt = null;
    @BindView(R.id.asim_atm_6dbUptxt) TextView asim6db_upTxt = null;
    @BindView(R.id.asim_atm_11dbDwTxt) TextView asim11db_dwTxt = null;
    @BindView(R.id.asim_atm_11dbUpTxt) TextView asim11db_upTxT = null;
    @BindView(R.id.asim_atm_12dbDwtxt) TextView asim12db_dwTxt = null;
    @BindView(R.id.asim_atm_12dbUptxt) TextView asim12db_upTxT = null;
    @BindView(R.id.asim_atm_distTxt) TextView asim_dist = null;





    private InfoLine shapeObj;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //init with butterknife
        ButterKnife.bind(this);

        gson = new Gson();
        String shapeLineAsString = getIntent().getStringExtra(Constants.intent_extra);
        InfoLine shapeObj = gson.fromJson(shapeLineAsString,InfoLine.class);


        if (shapeObj != null && shapeLineAsString != null && shapeLineAsString != "") {
            addressTxt.setText(writeAddress(shapeObj.nameLine()));
            fttcTxt.setText(shapeObj.getFttc());
            distCabTxt.setText(shapeObj.getDistCab());
            adslDwTxt.setText(shapeObj.getSpeedAdsl_down());
            adslUpTxt.setText(shapeObj.getSpeedAdsl_up());
            vdslStatusTxt.setText(shapeObj.getVdslStatus());
            vdslDwTxt.setText(shapeObj.getSpeedVdsl_down());
            vdslUpTxt.setText(shapeObj.getSpeedVdsl_up());
            sluStatusTxt.setText(shapeObj.getSlu_status());
            sluDwTxt.setText(shapeObj.getSluDw());
            sluUpTxt.setText(shapeObj.getSluUp());
            asim_atm_statusTxt.setText(shapeObj.getAsim_atm_status());
            asim6db_dwTxt.setText(shapeObj.getAsim_atm_6db_down());
            asim6db_upTxt.setText(shapeObj.getAsim_atm_6db_up());
            asim11db_dwTxt.setText(shapeObj.getAsim_atm_11ddb_down());
            asim11db_upTxT.setText(shapeObj.getAsim_atm_11db_up());
            asim12db_dwTxt.setText(shapeObj.getAsim_atm_12db_down());
            asim12db_upTxT.setText(shapeObj.getAsim_atm_12db_up());
            asim_dist.setText(shapeObj.getDist_asim_atm());
        }

    }

    //problem with table row. This method creates a suitable string for my layout
    private String writeAddress(String address){
        if (address.length() > 20) {
            return (address.substring(0,20) + "...");  // the 20 value is a generic value
        } else {
            return address;
        }
    }
}
