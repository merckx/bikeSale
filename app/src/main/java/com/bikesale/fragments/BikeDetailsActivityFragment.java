package com.bikesale.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bikesale.MainActivity;
import com.bikesale.R;
import com.bikesale.adapters.BikePartsAdapter;
import com.bikesale.models.Bike;

/**
 * A placeholder fragment containing a simple view.
 */
public class BikeDetailsActivityFragment extends Fragment
{
    private Bike bike;
    private BikePartsAdapter partsAdapter;

    public BikeDetailsActivityFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.bike = getActivity().getIntent().getParcelableExtra("BIKE");

    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_bike_details, container, false);
        EditText bikeName = view.findViewById(R.id.bike_name);
        bikeName.setText(this.bike.name);
        EditText bikePrice = view.findViewById(R.id.bike_price);
        bikePrice.setText(String.valueOf(this.bike.priceBought));
        partsAdapter = new BikePartsAdapter(bike);
        RecyclerView partsList = view.findViewById(R.id.parts_list);
        partsList.setAdapter(partsAdapter);
        partsList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }


}
