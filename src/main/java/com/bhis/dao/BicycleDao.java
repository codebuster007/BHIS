package com.bhis.dao;


import com.bhis.model.Bicycle;

import java.util.List;

public interface BicycleDao {

    List<Bicycle> findAll();
    Bicycle findById(String bicycleNo);
    List<Bicycle> findByName(String name);
    boolean insertBicycle(Bicycle bicycle);
    void updateBicycle(Bicycle bicycle);
    boolean deleteBicycle(Bicycle bicycle);
}
