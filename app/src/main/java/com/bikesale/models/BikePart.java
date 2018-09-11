package com.bikesale.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BikePart implements Parcelable
{
    public int type;
    public String name;
    public String model;
    public double minSellPrice;
    public double sellPrice;
    public double priceSold;

    public BikePart(int type)
    {
        this.type = type;
    }

    public BikePart(String name, int type)
    {
        this.name = name;
        this.type = type;
    }

    protected BikePart(Parcel in)
    {
        type = in.readInt();
        name = in.readString();
        model = in.readString();
        minSellPrice = in.readDouble();
        sellPrice = in.readDouble();
        priceSold = in.readDouble();
    }

    public static final Creator<BikePart> CREATOR = new Creator<BikePart>()
    {
        @Override
        public BikePart createFromParcel(Parcel in)
        {
            return new BikePart(in);
        }

        @Override
        public BikePart[] newArray(int size)
        {
            return new BikePart[size];
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
        dest.writeInt(type);
        dest.writeString(name);
        dest.writeString(model);
        dest.writeDouble(minSellPrice);
        dest.writeDouble(sellPrice);
        dest.writeDouble(priceSold);
    }
}
