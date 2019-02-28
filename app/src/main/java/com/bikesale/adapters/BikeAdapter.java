package com.bikesale.adapters;

import com.bikesale.R;
import com.bikesale.models.Bike;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class BikeAdapter extends FirebaseRecyclerAdapter<Bike, BikeHolder>
{
    public BikeAdapter()
    {
        super(Bike.class,
                R.layout.bike_item,
                BikeHolder.class,
                FirebaseDatabase.getInstance().getReference(Bike.TABLE_NAME));
    }


    @Override
    protected void populateViewHolder(BikeHolder viewHolder, Bike model, int position)
    {
        viewHolder.bind(model);
    }
}
