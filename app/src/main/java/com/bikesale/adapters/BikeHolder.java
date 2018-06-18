package com.bikesale.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bikesale.R;
import com.bikesale.events.OpenBikeDetailsEvent;
import com.bikesale.models.Bike;

import org.greenrobot.eventbus.EventBus;

public class BikeHolder
        extends RecyclerView.ViewHolder
{
    private TextView bikeName;
    private TextView bikePrice;
    public BikeHolder(final View itemView)
    {
        super(itemView);
        bikeName = itemView.findViewById(R.id.bike_name);
        bikePrice = itemView.findViewById(R.id.bike_price);
    }

    public void bind(final Bike bike)
    {
        this.itemView.setOnClickListener(listener -> EventBus.getDefault().post(new OpenBikeDetailsEvent(bike)));
        bikeName.setText(bike.name);
        bikePrice.setText(String.valueOf(bike.priceBought));
    }
}
