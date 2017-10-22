package com.stefanodecillis.intcoverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {


    private InfoLine shapeObj = null;

    //UI objects
    @BindView(R.id.addressTxt)  TextView addressTxt = null;
    @BindView(R.id.fttcTxt)  TextView fttcTxt = null;
    @BindView(R.id.distCabTxt)  TextView distCabTxt = null;
    @BindView(R.id.adslDwTxt)  TextView adslDwTxt = null;
    @BindView(R.id.adslUpTxt)  TextView adslUpTxt = null;
    @BindView(R.id.vdslStatusTxt)  TextView vdslStatusTxt = null;
    @BindView(R.id.vdslDwTxt)  TextView vdslDwTxt = null;
    @BindView(R.id.vdslUpTxt)  TextView vdslUpTxt = null;
    @BindView(R.id.sluDwTxt)  TextView sluDwTxt = null;
    @BindView(R.id.sluUpTxt) TextView sluUpTxt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //init UI
        ButterKnife.bind(this);

        Gson gson = new Gson();


        String shapeLineAsString = getIntent().getStringExtra("shapeLineObj");
        if (shapeLineAsString != null) {
            shapeObj = gson.fromJson(shapeLineAsString, InfoLine.class);
        }

        if (shapeObj != null) {
            addressTxt.setText(shapeObj.nameLine() + "  ");
            fttcTxt.setText(shapeObj.getFttc());
            distCabTxt.setText(shapeObj.getDistCab());
            adslDwTxt.setText(shapeObj.getSpeedAdsl_down());
            adslUpTxt.setText(shapeObj.getSpeedAdsl_up());
            vdslStatusTxt.setText(shapeObj.getVdslStatus());
            vdslDwTxt.setText(shapeObj.getSpeedVdsl_down());
            vdslUpTxt.setText(shapeObj.getSpeedVdsl_up());
            sluDwTxt.setText(shapeObj.getSpeedSlu_down());
            sluUpTxt.setText(shapeObj.getSpeedSlu_up());
        }
    }

}
