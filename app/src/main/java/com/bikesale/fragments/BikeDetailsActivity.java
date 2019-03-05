package com.bikesale.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bikesale.R;
import com.bikesale.dialogs.AddBikePartDialog;
import com.bikesale.events.SaveBikeEvent;
import com.bikesale.models.Bike;

public class BikeDetailsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bike bike = getIntent().getParcelableExtra(Bike.PARCELABLE_NAME);
        FloatingActionButton fab = findViewById(R.id.add_part);
        fab.setOnClickListener(view ->
        {
            AddBikePartDialog.show(getSupportFragmentManager(), bike);
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bike_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.save_bike:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                new SaveBikeEvent().execute();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


