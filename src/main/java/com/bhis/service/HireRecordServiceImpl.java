package com.bhis.service;

import com.bhis.dao.HireRecordDao;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.HireRecord;

import java.util.Date;
import java.util.List;

public class HireRecordServiceImpl implements HireRecordService {

    private HireRecordDao hireRecordDao;

    public HireRecordServiceImpl(HireRecordDao hireRecordDao) {
        this.hireRecordDao = hireRecordDao;
    }

    @Override
    public List<HireRecord> getAllHireRecords() {
        return hireRecordDao.findAll();
    }

    @Override
    public List<HireRecord> getAllReturnedHireRecords() {
        return hireRecordDao.findAllReturned();
    }

    @Override
    public List<HireRecord> getAllHiredHireRecords() {
        return hireRecordDao.findAllHired();
    }

    @Override
    public HireRecord getHireRecordById(String hireRecordId) {
        return hireRecordDao.findById(hireRecordId);
    }

    @Override
    public List<HireRecord> getHireRecordByBicycle(Bicycle bicycle) {
        return hireRecordDao.findByBicycle(bicycle);
    }

    @Override
    public List<HireRecord> getHireRecordByCustomer(Customer customer) {
        return hireRecordDao.findByCustomer(customer);
    }

    @Override
    public List<HireRecord> getHireRecordByHireDate(Date hireDate) {
        return hireRecordDao.findByHireDate(hireDate);
    }

    @Override
    public List<HireRecord> getHireRecordByReturnDate(Date returnDate) {
        return hireRecordDao.findByReturnDate(returnDate);
    }

    @Override
    public boolean addHireRecord(HireRecord hireRecord) {
        return hireRecordDao.insertHireRecord(hireRecord);
    }

    @Override
    public void updateHireRecord(HireRecord hireRecord) {
        hireRecordDao.updateHireRecord(hireRecord);
    }

    @Override
    public boolean removeHireRecord(HireRecord hireRecord) {
        return hireRecordDao.deleteHireRecord(hireRecord);
    }
}
