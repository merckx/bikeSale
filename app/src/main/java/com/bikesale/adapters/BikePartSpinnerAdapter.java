package com.bikesale.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bikesale.models.BikePart;
import com.bikesale.models.BikePartType;

import java.util.List;

public class BikePartSpinnerAdapter extends BaseAdapter {

    private List<BikePart> data;
    private LayoutInflater inflater;
    //private Context context;

    public BikePartSpinnerAdapter(Context context, List<BikePart> parts) {
        super();
        this.data = parts;
      //  this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override

    public BikePart getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_spinner_item, null);
        TextView tv = view.findViewById(android.R.id.text1);
        tv.setText(BikePartType.getName(data.get(position).type));
        return view;
    }
}
