package com.bikesale.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bikesale.R;
import com.bikesale.common.SemicolonWrapper;
import com.bikesale.models.BikePart;

public class BikePartViewHolder extends RecyclerView.ViewHolder
{
    private TextView partName;
    private TextView partModel;

    public BikePartViewHolder(View itemView)
    {
        super(itemView);
        partName = itemView.findViewById(R.id.part_name);
        partModel = itemView.findViewById(R.id.part_model);
    }

    public void bind(BikePart part)
    {
        partName.setText(new SemicolonWrapper(part.name).getText());
        partModel.setText(part.model);
    }



}
