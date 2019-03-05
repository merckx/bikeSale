package com.bikesale.events;

import com.bikesale.models.BikePart;

import org.greenrobot.eventbus.EventBus;

public class SellBikePartEvent {
    private BikePart part;

    public SellBikePartEvent(BikePart part) {
        this.part = part;
    }

    public BikePart getPart() {
        return part;
    }

    public void execute() {
        EventBus.getDefault().post(this);
    }
}
