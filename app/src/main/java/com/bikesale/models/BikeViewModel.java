package com.bikesale.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BikeViewModel extends ViewModel {
    public LiveData<Bike> bikeLiveData;
    public void setBike(Bike bike)
    {
        bikeLiveData = new MutableLiveData<>();
        ((MutableLiveData<Bike>) bikeLiveData).setValue(bike);
    }


}
