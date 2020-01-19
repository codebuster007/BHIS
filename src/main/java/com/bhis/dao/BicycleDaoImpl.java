package com.bhis.dao;

import com.bhis.database.BicycleDatabase;
import com.bhis.model.Bicycle;
import com.bhis.model.Bicycle.BicycleType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An implementation of a BicycleDao which simulates a JPA Repository
 * This can be removed after database functionality has been added
 */
public class BicycleDaoImpl implements BicycleDao {


    @Override
    public List<Bicycle> findAll() {
        return BicycleDatabase.getInstance()
                .getBicycleList();
    }

    @Override
    public Bicycle findByNumber(String bicycleNo) {
        List<Bicycle> bicycleList = BicycleDatabase.getInstance().getBicycleList();

        return bicycleList.stream()
                .filter( bicycle -> bicycle.getBicycleNo().equals(bicycleNo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Bicycle> findByMake(String make) {
        List<Bicycle> bicycleList = BicycleDatabase.getInstance().getBicycleList();

        return bicycleList.stream()
                .filter( bicycle -> bicycle.getBicycleMake().equals(make))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bicycle> findByModel(String model) {
        List<Bicycle> bicycleList = BicycleDatabase.getInstance().getBicycleList();

        return bicycleList.stream()
                .filter( bicycle -> bicycle.getBicycleModel().equals(model))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bicycle> findByType(BicycleType type) {
        List<Bicycle> bicycleList = BicycleDatabase.getInstance().getBicycleList();

        return bicycleList.stream()
                .filter( bicycle -> bicycle.getBicycleType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertBicycle(Bicycle bicycle) {
        BicycleDatabase database = BicycleDatabase.getInstance();
        database.addBicycle(bicycle);

        return true;
    }

    @Override
    public void updateBicycle(Bicycle bicycle) {
        BicycleDatabase database = BicycleDatabase.getInstance();
        int index = database.getBicycleList().indexOf(bicycle);

        database.getInstance()
                .getBicycleList()
                .set(index, bicycle);
    }

    @Override
    public boolean deleteBicycle(Bicycle bicycle) {
        BicycleDatabase.getInstance()
                .getBicycleList()
                .remove(bicycle);

        return true;
    }
}
