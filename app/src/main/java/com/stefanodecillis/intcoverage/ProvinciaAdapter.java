package com.stefanodecillis.intcoverage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stefanodecillis on 19/10/2017.
 */

public class ProvinciaAdapter extends ArrayAdapter<Provincia> {

    private static class ViewHolder {
        private AutoCompleteTextView itemView;
    }

    public ProvinciaAdapter(Context context, int textViewResourceId, ArrayList<Provincia> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (AutoCompleteTextView) convertView.findViewById(R.id.autocomplete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Provincia item = getItem(position);
        if (item != null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(item.name);  //error. Idk why!
        }

        return convertView;
    }
}
