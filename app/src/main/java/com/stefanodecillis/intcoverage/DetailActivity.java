package com.stefanodecillis.intcoverage;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.stefanodecillis.intcoverage.Entities.InfoLine;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    /*
    *  API forces me to write this bindings
    *
    *
    * probably I could use listview with adapter --> I should write each line with new strings --> same code
    *
    *
     */
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
    @BindView(R.id.my_toolbar)
    android.support.v7.widget.Toolbar myToolbar = null;
    @BindView(R.id.asim_eth_StatusTxt) TextView asim_status = null;
    @BindView(R.id.asim_eth_6dbDwTxt) TextView asim6db_eth_dwTxt = null;
    @BindView(R.id.asim_eth_6dbUptxt) TextView asim6db_eth_upTxt = null;
    @BindView(R.id.asim_eth_11dbDwTxt) TextView asim11db_eth_dwTxt = null;
    @BindView(R.id.asim_eth_11dbUpTxt) TextView asim11db_eth_upTxT = null;
    @BindView(R.id.asim_eth_12dbDwtxt) TextView asim12db_eth_dwTxt = null;
    @BindView(R.id.asim_eth_12dbUptxt) TextView asim12db_eth_upTxT = null;
    @BindView(R.id.asim_eth_distTxt) TextView asim_eth_dist = null;
    @BindView(R.id.simm_ethTxt) TextView simm_eth = null;
    @BindView(R.id.simm_atm_statusTxt) TextView simm_atm_status = null;
    @BindView(R.id.ull_statusTxt) TextView ull_status = null;
    @BindView(R.id.ull_adsl_downTxt) TextView ull_adsl_down = null;
    @BindView(R.id.ull_adsl_upTxt) TextView ull_adsl_up = null;
    @BindView(R.id.ull_vdsl_downTxt) TextView ull_vdsl_down = null;
    @BindView(R.id.ull_vdsl_upTxt) TextView ull_vdsl_up = null;
    @BindView(R.id.wlr_statusTxt) TextView wlr_status = null;
    @BindView(R.id.fibra_eth_statusTxt) TextView fibra_eth = null;
    @BindView(R.id.fibra_eth_fasciaTxt) TextView fibra_fascia = null;
    @BindView(R.id.edrTxt) TextView edr = null;
    @BindView(R.id.edr_typeTxt) TextView edr_type = null;
    @BindView(R.id.ftthTxt) TextView ftth_status = null;
    @BindView(R.id.edr_fttcTxt) TextView edr_fttc = null;
    @BindView(R.id.adslFttcDwTxt) TextView adslFttcDw = null;
    @BindView(R.id.adslFttcUpTxt) TextView adslFttcUp = null;

    private InfoLine shapeObj;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //init with butterknife
        ButterKnife.bind(this);

        /*
        reflection from json to class
         */
        gson = new Gson();
        String shapeLineAsString = getIntent().getStringExtra(Constants.intent_extra);
        shapeObj = gson.fromJson(shapeLineAsString,InfoLine.class);


        /* setting up toolbar */
        getSupportToolbar();


        if (shapeObj != null && shapeLineAsString != null && shapeLineAsString != "") {
            iniUI();
        }

        //backButton on toolbar clicked
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#0085c1"));
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

    private void iniUI(){
        addressTxt.setText(writeAddress(shapeObj.nameLine()));
        fttcTxt.setText(shapeObj.getNga_vula_Status());
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
        asim_status.setText(shapeObj.getAsim_eth_Status());
        asim6db_eth_dwTxt.setText(shapeObj.getAsim_eth_6db_down());
        asim6db_eth_upTxt.setText(shapeObj.getAsim_eth_6db_up());
        asim11db_eth_dwTxt.setText(shapeObj.getAsim_eth_11ddb_down());
        asim11db_eth_upTxT.setText(shapeObj.getAsim_eth_11db_up());
        asim12db_eth_dwTxt.setText(shapeObj.getAsim_eth_12db_down());
        asim12db_eth_upTxT.setText(shapeObj.getAsim_eth_12db_up());
        asim_eth_dist.setText(shapeObj.getDist_asim_eth());
        simm_atm_status.setText(shapeObj.getSimm_atm_status());
        simm_eth.setText(shapeObj.getSimm_eth_status());
        ull_status.setText(shapeObj.getUll_Status());
        ull_adsl_down.setText(shapeObj.getUll_adsl2_down());
        ull_adsl_up.setText(shapeObj.getUll_adsl2_up());
        ull_vdsl_down.setText(shapeObj.getUll_vdsl_down());
        ull_vdsl_up.setText(shapeObj.getUll_vdsl_up());
        wlr_status.setText(shapeObj.getWlr_status());
        fibra_eth.setText(shapeObj.getTerm_eth_fibr());
        fibra_fascia.setText(shapeObj.getFibra_eth_fascia());
        edr.setText(shapeObj.getEdr());
        edr_type.setText(shapeObj.getEdr_type());
        ftth_status.setText(shapeObj.getFtth_status());
        edr_fttc.setText(shapeObj.getEdr_fttc());
        adslFttcDw.setText(shapeObj.getSpeedAdslFttc_down());
        adslFttcUp.setText(shapeObj.getSpeedAdslFttc_up());
    }

    private void getSupportToolbar(){
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
