package com.bikesale.fragments;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bikesale.R;
import com.bikesale.adapters.BikePartsAdapter;
import com.bikesale.adapters.OnPartsDataChanged;
import com.bikesale.dialogs.SellBikePartDialog;
import com.bikesale.events.DeleteBikePartEvent;
import com.bikesale.events.SaveBikeEvent;
import com.bikesale.events.SaveBikePartEvent;
import com.bikesale.events.SellBikePartEvent;
import com.bikesale.models.Bike;
import com.bikesale.repository.BikeDao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A placeholder fragment containing a simple view.
 */
public class BikeDetailsActivityFragment extends Fragment {
    private Bike bike;
    private BikePartsAdapter partsAdapter;
    //    private BikeViewModel bikeViewModel;
    private static final String TAG = "BikeDetailsFragment";
    private EditText bikeName;
    private EditText bikePrice;
    private TextView totalSold;
    private TextView minCalculatedProfit;
    private TextView calculatedProfit;
    private TextView realProfit;

    public BikeDetailsActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bike = getActivity().getIntent().getParcelableExtra(Bike.PARCELABLE_NAME);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bike_details, container, false);

        bikeName = view.findViewById(R.id.bike_name);
        bikeName.setText(this.bike.name);
        bikePrice = view.findViewById(R.id.bike_price);
        totalSold = view.findViewById(R.id.total_sold);
        minCalculatedProfit = view.findViewById(R.id.min_calculated_profit);
        calculatedProfit = view.findViewById(R.id.calculated_profit);
        realProfit = view.findViewById(R.id.real_profit);

        bikePrice.setText(String.valueOf(this.bike.priceBought));
        partsAdapter = new BikePartsAdapter(bike);
        RecyclerView partsList = view.findViewById(R.id.parts_list);

        partsList.setAdapter(partsAdapter);
        partsList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        OnPartsDataChanged listener = createPartsChangedListener();
        partsAdapter.setOnPartsDataChangedListener(listener);

        return view;
    }

    private OnPartsDataChanged createPartsChangedListener()
    {
        return new OnPartsDataChanged() {
            @Override
            public void totalSoldChanged(double sold) {
                totalSold.setText("Sold: " + String.valueOf(sold));
                double realProfitSum = sold - bike.priceBought;
                realProfit.setText("Real Profit: " + realProfitSum);
                //realProfit.setBackgroundColor(realProfitSum > 0 ? Color.GREEN : Color.RED);
            }

            @Override
            public void totalCalculatedProfitsChanged(double totalCalculated, double minTotalCalculated) {
                calculatedProfit.setText("Calculated Profit: " + String.valueOf(totalCalculated));
                minCalculatedProfit.setText("Min Profit: " + String.valueOf(minTotalCalculated));
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SaveBikePartEvent event) {
        BikeDao.saveBikePart(event.getBike(), event.getPart());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DeleteBikePartEvent event) {
        BikeDao.deleteBikePart(this.bike, event.getPart());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SaveBikeEvent event) {
        EditText bikeName = getView().findViewById(R.id.bike_name);
        EditText bikePrice = getView().findViewById(R.id.bike_price);
        this.bike.name = bikeName.getText().toString();
        //todo check if it is not empty string
        this.bike.priceBought = Double.parseDouble(bikePrice.getText().toString());
        BikeDao.updateBike(this.bike);
        getActivity().finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SellBikePartEvent event) {
        SellBikePartDialog.Companion.show(getFragmentManager(), event.getPart(), this.bike);
    }


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

            // A new comment has been added, add it to the displayed list
//            BikePart comment = dataSnapshot.getValue(BikePart.class);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
//            BikePart newComment = dataSnapshot.getValue(BikePart.class);
//            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
//            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

            // A comment has changed position, use the key to determine if we are
//            // displaying this comment and if so move it.
//            BikePart movedComment = dataSnapshot.getValue(BikePart.class);
//            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//            Toast.makeText(, "Failed to load comments.",
//                    Toast.LENGTH_SHORT).show();
        }
    };


}
