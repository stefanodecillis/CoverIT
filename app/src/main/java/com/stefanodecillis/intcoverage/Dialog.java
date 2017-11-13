package com.stefanodecillis.intcoverage;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dialog extends Activity {

    @BindView(R.id.expListView)
    ExpandableListView expandableListView = null;

    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //bind UI
        ButterKnife.bind(this);

        /*
        setting display
         */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        /*
         finished setting display
         */

        // preparing list data
        prepareListData();

        listAdapter = new com.stefanodecillis.intcoverage.ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("NGA VULA");
        listDataHeader.add("FTTx");
        listDataHeader.add("SLU");
        listDataHeader.add("Bitstream");
        listDataHeader.add("ULL");

        // Adding child data
        List<String> nga_vula = new ArrayList<String>();
        nga_vula.add(Constants.NGA_VULA_DESCR);

        List<String> fttx = new ArrayList<String>();
        fttx.add(Constants.FTTC_DESCR);
        fttx.add(Constants.FTTH_DESCR);

        List<String> slu = new ArrayList<String>();
        slu.add(Constants.SLU_DESCR);

        List<String> bitstream = new ArrayList<String>();
        bitstream.add(Constants.BITSTREAM_DESCR);
        List<String> ull = new ArrayList<String>();
        ull.add(Constants.ULL_DESCR);

        listDataChild.put(listDataHeader.get(0), nga_vula); // Header, Child data
        listDataChild.put(listDataHeader.get(1), fttx);
        listDataChild.put(listDataHeader.get(2), slu);
        listDataChild.put(listDataHeader.get(3), bitstream);
        listDataChild.put(listDataHeader.get(4), ull);
    }
}
