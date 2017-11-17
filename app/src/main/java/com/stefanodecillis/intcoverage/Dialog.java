package com.stefanodecillis.intcoverage;

/**
 * Dialog with exListView
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
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

        /*
        setting display
         */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        //bind UI
        View v = LayoutInflater.from(this).inflate(R.layout.activity_dialog, null);
        ButterKnife.bind(this, v);

        android.app.Dialog d = new android.app.Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.addContentView(v, new RelativeLayout.LayoutParams((int) (width * .8), (int) (height * .6)));
        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        d.show();

        // preparing list data
        prepareListData();

        listAdapter = new com.stefanodecillis.intcoverage.ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#0085c1"));
        }
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
