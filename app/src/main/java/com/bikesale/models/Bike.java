package com.bikesale.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bike implements Parcelable
{
    public String id;
    public String name;
    public List<BikePart> parts;
    public Double priceBought;

    public Bike()
    {
        id = UUID.randomUUID().toString();
        parts = new ArrayList<>();
        priceBought = 0d;
    }

    public Bike(final String name, final double price)
    {
        id = UUID.randomUUID().toString();
        parts = new ArrayList<>();
        this.name = name;
        this.priceBought = price;
    }

    protected Bike(Parcel in)
    {
        id = in.readString();
        name = in.readString();
        priceBought = in.readDouble();
        parts = in.createTypedArrayList(BikePart.CREATOR);
    }

    public static final Creator<Bike> CREATOR = new Creator<Bike>()
    {
        @Override
        public Bike createFromParcel(Parcel in)
        {
            return new Bike(in);
        }

        @Override
        public Bike[] newArray(int size)
        {
            return new Bike[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(priceBought);
        dest.writeTypedList(parts);
    }
}
