package com.bikesale.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bikesale.R;
import com.bikesale.adapters.BikePartSpinnerAdapter;
import com.bikesale.events.AddNewBikePartEvent;
import com.bikesale.models.BikePart;
import com.bikesale.repository.BikePartsProvider;

import org.greenrobot.eventbus.EventBus;

public class AddBikePartDialog extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_part_view, container, false);
        Spinner spinner = v.findViewById(R.id.choose_part_spinner);
        BikePartSpinnerAdapter dataAdapter = new BikePartSpinnerAdapter(this.getActivity(),
                BikePartsProvider.getPartsList());
        spinner.setAdapter(dataAdapter);

        EditText name = v.findViewById(R.id.part_name);
        EditText minSellPrice = v.findViewById(R.id.part_min_price);
        EditText sellPrice = v.findViewById(R.id.part_price);

        Button btnAdd = v.findViewById(R.id.add_part);
        btnAdd.setOnClickListener(l -> {
            int selectedPartType = dataAdapter.getItem(spinner.getSelectedItemPosition()).type;
            BikePart part = BikePartsProvider.newBikePart(selectedPartType,
                    name.getText().toString(),
                    Double.parseDouble(minSellPrice.getText().toString()),
                    Double.parseDouble(sellPrice.getText().toString()));
            EventBus.getDefault().post(new AddNewBikePartEvent(part));
            dismiss();
        });
        return v;
    }

}
