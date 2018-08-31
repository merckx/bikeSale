package com.bikesale.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bikesale.R;
import com.bikesale.dialogs.AddBikePartDialog;

public class BikeDetailsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.add_part);
        fab.setOnClickListener(view ->
        {
            AddBikePartDialog dialog = new AddBikePartDialog();
            dialog.show(getSupportFragmentManager(), "addpartdialog");
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
