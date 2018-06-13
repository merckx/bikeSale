package com.bikesale.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bike {
    public String id;
    public String name;
    public List<BikePart> parts;
    public Double priceBought;
    public Bike()
    {
        id = UUID.randomUUID().toString();
        parts = new ArrayList<>();
    }
}
