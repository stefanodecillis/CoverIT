package com.stefanodecillis.intcoverage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stefanodecillis.intcoverage.Entities.InfoLine;

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
    @BindView(R.id.infoBtn)
    Button infoBtn = null;


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

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
                 ListItem listItem = (ListItem) listView.getAdapter().getItem(pos);
                Toast.makeText(getApplicationContext(),"Cliccato "  + listItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
*/
        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDetail(shapeObj);
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, Dialog.class));
            }
        });

    }


    private void initUI(List<ListItem> list){
        list.add(new ListItem(Constants.SLU,shapeObj.getSlu_status()));
        list.add(new ListItem(Constants.NGA_VULA, shapeObj.getNga_vula_Status()));
        list.add(new ListItem(Constants.FIBR_FTTH, shapeObj.getFtth_status()));
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

    /*
     * method for toolbar
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         if (id == R.id.infoBtn) {
             Toast.makeText(getApplicationContext(), "infoBTN clicked from toolbar", Toast.LENGTH_SHORT).show();
             return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
