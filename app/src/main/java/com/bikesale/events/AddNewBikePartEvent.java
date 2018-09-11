package com.bikesale.events;

import com.bikesale.models.BikePart;

public class AddNewBikePartEvent {
    private BikePart part;

    public AddNewBikePartEvent(BikePart part) {
        this.part = part;
    }

    public BikePart getPart() {
        return part;
    }
}
