package com.bikesale.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bikesale.MainActivity;
import com.bikesale.R;
import com.bikesale.adapters.BikePartsAdapter;
import com.bikesale.events.AddNewBikePartEvent;
import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;
import com.bikesale.repository.BikeDao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A placeholder fragment containing a simple view.
 */
public class BikeDetailsActivityFragment extends Fragment
{
    private Bike bike;
    private BikePartsAdapter partsAdapter;

    private static final String TAG = "BIKEDETAILSACTIVITY";

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
//        FirebaseDatabase.getInstance()
//                .getReference().addChildEventListener(childEventListener);
        return view;
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
    public void onMessageEvent(AddNewBikePartEvent event) {
        BikeDao.addBikePart(this.bike, event.getPart());
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
