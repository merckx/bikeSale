package com.bikesale.repository;

import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;
import com.google.firebase.database.FirebaseDatabase;

public class BikeDao
{
    public static void addBike(Bike bike)
    {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(Bike.TABLE_NAME)
                .child(bike.id)
                .setValue(bike);
    }

    public static void addBikePart(Bike bike, BikePart part)
    {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(BikePart.TABLE_NAME)
                .child(bike.id)
                .child(part.name)
                .setValue(part);
    }

//    public static void getBike()

}
