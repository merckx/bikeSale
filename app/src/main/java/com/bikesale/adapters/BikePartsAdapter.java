package com.bikesale.adapters;

import com.bikesale.R;
import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class BikePartsAdapter extends FirebaseRecyclerAdapter<BikePart, BikePartViewHolder>
{
    private OnPartsDataChanged mListener;
    public BikePartsAdapter(Bike bike)
    {
        super(BikePart.class,
                R.layout.bike_part_item,
                BikePartViewHolder.class,
                FirebaseDatabase.getInstance().getReference(BikePart.TABLE_NAME).child(bike.id));
    }


    @Override
    protected void populateViewHolder(BikePartViewHolder viewHolder, BikePart model, int position)
    {
        viewHolder.bind(model);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        calcTotals();
    }

    private void calcTotals() {
        double earned = 0;
        double profit = 0;
        double minProfit = 0;
        for (int pos = 0; pos < getItemCount(); pos++)
        {
            BikePart part = getItem(pos);
            earned = earned + part.priceSold;
            profit = profit + part.sellPrice;
            minProfit = minProfit + part.minSellPrice;
        }

        if (mListener != null)
        {
            mListener.totalSoldChanged(earned);
            mListener.totalCalculatedProfitsChanged(profit, minProfit);
        }
    }

    public void setOnPartsDataChangedListener(OnPartsDataChanged listener)
    {
        this.mListener = listener;
    }

}
