package com.bhis.service;

import com.bhis.dao.BicycleDao;
import com.bhis.model.Bicycle;
import com.bhis.model.Bicycle.BicycleType;

import java.util.List;

public class BicycleServiceImpl implements BicycleService {

    private BicycleDao bicycleDao;

    public BicycleServiceImpl(BicycleDao bicycleDao) {
        this.bicycleDao = bicycleDao;
    }

    @Override
    public List<Bicycle> getAllBicycles() {
        return bicycleDao.findAll();
    }

    @Override
    public Bicycle getBicycleByNumber(String bicycleNo) {
        return bicycleDao.findByNumber(bicycleNo);
    }

    @Override
    public List<Bicycle> getBicycleByMake(String make) {
        return bicycleDao.findByMake(make);
    }

    @Override
    public List<Bicycle> getBicycleModel(String model) {
        return bicycleDao.findByModel(model);
    }

    @Override
    public List<Bicycle> getBicycleType(BicycleType type) {
        return bicycleDao.findByType(type);
    }

    @Override
    public boolean addBicycle(Bicycle bicycle) {
        return bicycleDao.insertBicycle(bicycle);
    }

    @Override
    public void updateBicycle(Bicycle bicycle) {
        bicycleDao.updateBicycle(bicycle);
    }

    @Override
    public boolean removeBicycle(Bicycle bicycle) {
        return bicycleDao.deleteBicycle(bicycle);
    }
}
