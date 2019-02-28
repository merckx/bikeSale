package com.bikesale.events;

import com.bikesale.models.BikePart;

import org.greenrobot.eventbus.EventBus;

public class DeleteBikePartEvent {
    private BikePart part;

    public DeleteBikePartEvent(BikePart part) {
        this.part = part;
    }

    public BikePart getPart() {
        return part;
    }

    public void execute()
    {
        EventBus.getDefault().post(this);
    }
}
