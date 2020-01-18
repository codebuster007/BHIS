package com.bhis.database;

import com.bhis.model.Bicycle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * A singleton that will hold bicycle data
 */
public class BicycleDatabase {
    private static BicycleDatabase mInstance;

    @Getter
    @Setter
    private List<Bicycle> bicycleList = null;

    public static BicycleDatabase getInstance(){
        if(mInstance == null)
            mInstance = new BicycleDatabase();

        return mInstance;
    }

    private BicycleDatabase(){
        bicycleList = new ArrayList<>();
    }

    public boolean addBicycle(Bicycle bicycle){
        if(!bicycleList.contains(bicycle)) {
            bicycleList.add(bicycle);
            return true;
        }
        return false;
    }
}
