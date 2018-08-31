package com.bikesale.adapters;

import com.bikesale.R;
import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class BikePartsAdapter extends FirebaseRecyclerAdapter<BikePart, BikePartViewHolder>
{
    public BikePartsAdapter(Bike bike)
    {
        super(BikePart.class,
                R.layout.bike_part_item,
                BikePartViewHolder.class,
                FirebaseDatabase.getInstance().getReference("parts").child(bike.id));
    }


    @Override
    protected void populateViewHolder(BikePartViewHolder viewHolder, BikePart model, int position)
    {
        viewHolder.bind(model);
    }
}
