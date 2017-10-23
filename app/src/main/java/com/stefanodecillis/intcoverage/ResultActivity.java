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

        String shapeLineAsString = getIntent().getStringExtra("shapeLineObj");
        if (shapeLineAsString != null) {
            shapeObj = gson.fromJson(shapeLineAsString, InfoLine.class);
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
        if (shapeObj.getVdslStatus().equalsIgnoreCase("Attivo")){
            vdslView.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getVdslStatus().equalsIgnoreCase("-")){
            vdslView.setImageResource(R.drawable.ic_yellow);
        } else {
            vdslView.setImageResource(R.drawable.ic_red);
        }
        if (shapeObj.getSimm_eth_status().equalsIgnoreCase("Attivo")){
            simmView.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getSimm_eth_status().equalsIgnoreCase("NO")){
            simmView.setImageResource(R.drawable.ic_red);
        } else {
            simmView.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getNga_vula_Status().equalsIgnoreCase("Attivo")){
            nga_vula_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getNga_vula_Status().equalsIgnoreCase("NO")){
            nga_vula_img.setImageResource(R.drawable.ic_red);
        } else {
            nga_vula_img.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getAsim_atm_status().equalsIgnoreCase("Attivo")){
            asim_atm_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getAsim_atm_status().equalsIgnoreCase("NO")){
            asim_atm_img.setImageResource(R.drawable.ic_red);
        }  else {
            asim_atm_img.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getAsim_eth_Status().equalsIgnoreCase("Attivo")){
            asim_eth_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getAsim_eth_Status().equalsIgnoreCase("NO")){
            asim_eth_img.setImageResource(R.drawable.ic_red);
        } else {
            asim_eth_img.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getSimm_atm_status().equalsIgnoreCase("Attivo")){
            simm_atm_imgg.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getAsim_eth_Status().equalsIgnoreCase("NO")){
            simm_atm_imgg.setImageResource(R.drawable.ic_red);
        } else {
            simm_atm_imgg.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getUll_Status().equalsIgnoreCase("Attivo")){
            ull_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getUll_Status().equalsIgnoreCase("NO")){
            ull_img.setImageResource(R.drawable.ic_red);
        } else {
            ull_img.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getTerm_eth_fibr().equalsIgnoreCase("Attivo")){
            term_eth_fibr_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getTerm_eth_fibr().equalsIgnoreCase("NO")){
            term_eth_fibr_img.setImageResource(R.drawable.ic_red);
        } else {
            term_eth_fibr_img.setImageResource(R.drawable.ic_yellow);
        }
        if (shapeObj.getSlu_status().equalsIgnoreCase("SI")){
            slu_img.setImageResource(R.drawable.ic_green);
        } else if (shapeObj.getTerm_eth_fibr().equalsIgnoreCase("NO")){
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
        intent.putExtra("shapeLine", shapeLineString);
        startActivity(intent);
    }
}
