package com.bhis.service;

import com.bhis.model.Bicycle;
import com.bhis.model.Bicycle.BicycleType;

import java.util.List;

public interface BicycleService {

    List<Bicycle> getAllBicycles();
    Bicycle getBicycleByNumber(String bicycleNo);
    List<Bicycle> getBicycleByMake(String make);
    List<Bicycle> getBicycleModel(String model);
    List<Bicycle> getBicycleType(BicycleType type);
    boolean addBicycle(Bicycle bicycle);
    void updateBicycle(Bicycle bicycle);
    boolean removeBicycle(Bicycle bicycle);
}
