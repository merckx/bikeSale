package com.bikesale.repository;

import com.bikesale.models.Bike;
import com.google.firebase.database.FirebaseDatabase;

public class BikeDao
{
    public static void addBike(Bike bike)
    {
        FirebaseDatabase.getInstance()
                .getReference()
                .child("bikes")
                .child(bike.id)
                .setValue(bike);
    }


}
