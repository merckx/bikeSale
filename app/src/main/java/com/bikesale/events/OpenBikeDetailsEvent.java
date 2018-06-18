package com.bikesale.events;

import com.bikesale.models.Bike;

public class OpenBikeDetailsEvent
{
    private Bike bike;

    public OpenBikeDetailsEvent(final Bike bike)
    {
        this.bike = bike;
    }

    public Bike getBike()
    {
        return bike;
    }
}
