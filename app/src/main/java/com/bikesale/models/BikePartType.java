package com.bikesale.models;

import java.util.List;

public class BikePartType {
    public static final int BRAKES = 1;
    public static final int FRAME = 2;
    public static final int CRANK = 3;
    public static final int FORK = 4;
    public static final int FRONT_DERAILLEUR = 5;
    public static final int REAR_DERAILLEUR = 6;
    public static final int PEDALS = 7;
    public static final int SADDLE = 8;
    public static final int SEATPOST = 9;
    public static final int SHIFTERS = 10;
    public static final int STEM = 11;
    public static final int TYRES = 12;
    public static final int WHEELS = 13;


    public static String getName(int type)
    {
        switch (type)
        {
            case 1:
                return "Brakes";
            case 2:
                return "Frame";
            case 3:
                return "Crank";
            case 4:
                return "Fork";
            case 5:
                return "Front Derailleur";
            case 6:
                return "Rear Derailleur";
            case 7:
                return "Pedals";
            case 8:
                return "Saddle";
            case 9:
                return "Seatpost";
            case 10:
                return "Shifters";
            case 11:
                return "Stem";
            case 12:
                return "Tyres";
            case 13:
                return "Wheels";
            default:
                return "Unknown";

        }
    }
}
