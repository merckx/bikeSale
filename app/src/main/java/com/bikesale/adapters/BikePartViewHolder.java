package com.bikesale.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bikesale.R;
import com.bikesale.common.SemicolonWrapper;
import com.bikesale.events.DeleteBikePartEvent;
import com.bikesale.models.BikePart;

import org.greenrobot.eventbus.EventBus;

public class BikePartViewHolder extends RecyclerView.ViewHolder {
    private TextView partName;
    private TextView partModel;
    private TextView minPriceToSell;
    private TextView priceToSell;
    private TextView priceSold;
    private ImageButton deletePartBtn;

    public BikePartViewHolder(View itemView) {
        super(itemView);
        partName = itemView.findViewById(R.id.part_name);
        partModel = itemView.findViewById(R.id.part_model);
        minPriceToSell= itemView.findViewById(R.id.min_price_to_sell);
        priceToSell = itemView.findViewById(R.id.price_to_sell);
        priceSold = itemView.findViewById(R.id.price_sold);
        deletePartBtn = itemView.findViewById(R.id.delete_part);
    }

    public void bind(BikePart part) {
        partName.setText(new SemicolonWrapper(part.name).getText());
        partModel.setText(part.model);
        minPriceToSell.setText("Min sell price:" + part.minSellPrice);
        priceToSell.setText("Sell price:" + part.sellPrice);
        priceSold.setText("Sold:" + part.priceSold);
        deletePartBtn.setOnClickListener(l -> new DeleteBikePartEvent(part).execute());

    }

}
