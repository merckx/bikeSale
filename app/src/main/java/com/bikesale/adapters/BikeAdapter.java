package com.bikesale.adapters;

import android.view.ViewGroup;

import com.bikesale.R;
import com.bikesale.models.Bike;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BikeAdapter extends FirebaseRecyclerAdapter<Bike, BikeHolder>
{
//
//    public BikeAdapter(ObservableSnapshotArray<Bike> dataSnapshots, int modelLayout, Class<BikeHolder> viewHolderClass)
//    {
//        super(dataSnapshots, modelLayout, viewHolderClass);
//    }
//
//    public BikeAdapter(SnapshotParser<Bike> parser, int modelLayout, Class<BikeHolder> viewHolderClass, Query query)
//    {
//        super(parser, modelLayout, viewHolderClass, query);
//    }

    public BikeAdapter()
    {
        super(Bike.class,
                R.layout.bike_item,
                BikeHolder.class,
                FirebaseDatabase.getInstance().getReference(Bike.TABLE_NAME));
    }

//    @Override
//    public BikeHolder onCreateViewHolder(ViewGroup parent, int viewType)
//    {
//        return super.onCreateViewHolder(parent, viewType);
//    }

    @Override
    protected void populateViewHolder(BikeHolder viewHolder, Bike model, int position)
    {
        viewHolder.bind(model);
    }
}
