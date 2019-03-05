package com.bikesale.events;

import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;

import org.greenrobot.eventbus.EventBus;

public class SaveBikePartEvent {
    private BikePart part;
    private Bike bike;

    public SaveBikePartEvent(Bike bike, BikePart part) {
        this.part = part;
        this.bike = bike;
    }

    public BikePart getPart() {
        return part;
    }

    public Bike getBike() {
        return bike;
    }

    public void execute() {
        EventBus.getDefault().post(this);
    }
}
