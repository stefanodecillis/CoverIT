package com.stefanodecillis.intcoverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity {

    protected TextView addressTxt = null;
    protected TextView fttcTxt = null;
    protected TextView distCabTxt = null;
    protected TextView adslDwTxt = null;
    protected TextView adslUpTxt = null;
    protected TextView vdslStatusTxt = null;
    protected TextView vdslDwTxt = null;
    protected TextView vdslUpTxt = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initUI();
        Gson gson = new Gson();
        String shapeLineAsString = getIntent().getStringExtra("shapeLineObj");
        ShapeLine shapeObj = gson.fromJson(shapeLineAsString,ShapeLine.class);

        addressTxt.setText(shapeObj.getAddress()+ "  ");
        fttcTxt.setText(shapeObj.getFttc());
        distCabTxt.setText(shapeObj.getDist_cab());
        adslDwTxt.setText(shapeObj.getAdsl_Dw());
        adslUpTxt.setText(shapeObj.getAdsl_Up());
        vdslStatusTxt.setText(shapeObj.getVdslStatus());
        vdslDwTxt.setText(shapeObj.getVdsl_Dw());
        vdslUpTxt.setText(shapeObj.getVdsl_Up());

        Log.d("RES", shapeObj.getVdsl_Dw());
        Log.d("RES", shapeObj.getVdsl_Up());
        Log.d("RES", shapeObj.getVdslStatus());
    }

    void initUI(){
        addressTxt = (TextView) findViewById(R.id.addressTxt);
        fttcTxt = (TextView) findViewById(R.id.fttcTxt);
        distCabTxt = (TextView) findViewById(R.id.distCabTxt);
        adslDwTxt = (TextView) findViewById(R.id.adslDwTxt);
        adslUpTxt = (TextView) findViewById(R.id.adslUpTxt);
        vdslStatusTxt = (TextView) findViewById(R.id.vdslStatusTxt);
        vdslDwTxt = (TextView) findViewById(R.id.vdslDwTxt);
        vdslUpTxt = (TextView) findViewById(R.id.vdslUpTxt);
    }
}
