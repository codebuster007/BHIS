package com.bhis.dao;


import com.bhis.model.Bicycle;

import java.util.List;

import static com.bhis.model.Bicycle.*;

/**
 * This can be replaced with a JPA Repository class
 *
 */
public interface BicycleDao {

    List<Bicycle> findAll();
    Bicycle findByNumber(String bicycleNo);
    List<Bicycle> findByMake(String make);
    List<Bicycle> findByModel(String model);
    List<Bicycle> findByType(BicycleType type);
    boolean insertBicycle(Bicycle bicycle);
    void updateBicycle(Bicycle bicycle);
    boolean deleteBicycle(Bicycle bicycle);
}
