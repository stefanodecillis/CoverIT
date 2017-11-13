package com.stefanodecillis.intcoverage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.OnItemClick;

/**
 * Created by stefanodecillis on 30/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<ListItem> {

    private List<ListItem> objects;

    static class ViewHolder {
        TextView title;
        ImageView image;
    }

    public CustomAdapter(Context context, int textViewResourceId,
                         List<ListItem> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    //https://stackoverflow.com/questions/22000208/android-custom-adapter-with-viewholder-oncheckedchangedlistener-and-textchange <---
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.image = (ImageView) convertView.findViewById(R.id.list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ListItem c = getItem(position);
        holder.title.setText(c.getTitle());
        if (c.getDescription().equalsIgnoreCase(Constants.active) || c.getDescription().equalsIgnoreCase("SI")){
            holder.image.setImageResource(R.drawable.ic_green);
        } else if (c.getDescription().equalsIgnoreCase(Constants.inactive)){
            holder.image.setImageResource(R.drawable.ic_red);
        } else {
            holder.image.setImageResource(R.drawable.ic_yellow);
        }
        return convertView;
    }

    //https://stackoverflow.com/questions/29201557/saving-and-restoring-listview-with-custom-list-adapter
    public List<ListItem> getList() {
        return ( List<ListItem>) objects;
    }
}
