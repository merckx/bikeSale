package com.bikesale.events;

import com.bikesale.models.Bike;
import com.bikesale.models.BikePart;

import org.greenrobot.eventbus.EventBus;

public class SaveBikeEvent {


    public SaveBikeEvent() {

    }


    public void execute() {
        EventBus.getDefault().post(this);
    }
}
