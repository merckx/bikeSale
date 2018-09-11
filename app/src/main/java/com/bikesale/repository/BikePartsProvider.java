package com.bikesale.repository;

import com.bikesale.models.BikePart;
import com.bikesale.models.BikePartType;
import com.bikesale.models.Brakes;
import com.bikesale.models.Crank;
import com.bikesale.models.Fork;
import com.bikesale.models.Frame;
import com.bikesale.models.FrontDerailleur;
import com.bikesale.models.Pedals;
import com.bikesale.models.RearDerailleur;
import com.bikesale.models.Saddle;
import com.bikesale.models.SeatPost;
import com.bikesale.models.Shifters;
import com.bikesale.models.Stem;
import com.bikesale.models.Tyres;
import com.bikesale.models.Wheels;

import java.util.ArrayList;
import java.util.List;

import static com.bikesale.models.BikePartType.BRAKES;
import static com.bikesale.models.BikePartType.CRANK;
import static com.bikesale.models.BikePartType.FORK;
import static com.bikesale.models.BikePartType.FRAME;
import static com.bikesale.models.BikePartType.FRONT_DERAILLEUR;
import static com.bikesale.models.BikePartType.PEDALS;
import static com.bikesale.models.BikePartType.REAR_DERAILLEUR;
import static com.bikesale.models.BikePartType.SADDLE;
import static com.bikesale.models.BikePartType.SEATPOST;
import static com.bikesale.models.BikePartType.SHIFTERS;
import static com.bikesale.models.BikePartType.STEM;
import static com.bikesale.models.BikePartType.TYRES;
import static com.bikesale.models.BikePartType.WHEELS;

public class BikePartsProvider {

    public static List<BikePart> getPartsList()
    {
        List<BikePart> parts = new ArrayList<>();
        parts.add(new Brakes());
        parts.add(new Crank());
        parts.add(new Fork());
        parts.add(new Frame());
        parts.add(new RearDerailleur());
        parts.add(new FrontDerailleur());
        parts.add(new Pedals());
        parts.add(new Saddle());
        parts.add(new SeatPost());
        parts.add(new Shifters());
        parts.add(new Stem());
        parts.add(new Tyres());
        parts.add(new Wheels());
        return parts;
    }

    public static BikePart newBikePart(int type,
                                       String name,
                                       double minSellPrice,
                                       double sellPrice)
    {
        BikePart part = null;
        switch (type) {
            case BRAKES:
                part = new Brakes();
                break;
            case CRANK:
                part = new Crank();
                break;
            case FORK:
                part = new Fork();
                break;
            case FRAME:
                part = new Frame();
                break;
            case REAR_DERAILLEUR:
                part = new RearDerailleur();
                break;
            case FRONT_DERAILLEUR:
                part = new FrontDerailleur();
                break;
            case PEDALS:
                part = new Pedals();
                break;
            case SADDLE:
                part = new Saddle();
                break;
            case SHIFTERS:
                part = new Shifters();
                break;
            case SEATPOST:
                part = new SeatPost();
                break;
            case TYRES:
                part = new Tyres();
                break;
            case WHEELS:
                part = new Wheels();
                break;
            case STEM:
                part = new Stem();
                break;
        }

        if (part != null)
        {
            part.sellPrice = sellPrice;
            part.minSellPrice = minSellPrice;
            part.name = name;
        }

        return part;
    }
}
