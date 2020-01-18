package com.bhis.dao;

import com.bhis.database.HireRecordDatabase;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.HireRecord;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HireRecordDaoImpl implements HireRecordDao {

    @Override
    public List<HireRecord> findAll() {
        return HireRecordDatabase.getInstance()
                .getHireRecordList();
    }

    @Override
    public List<HireRecord> findAllReturned() {

        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(HireRecord::isBicycleReturned)
                .collect(Collectors.toList());
    }

    @Override
    public List<HireRecord> findAllHired() {
        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(hireRecord -> !hireRecord.isBicycleReturned())
                .collect(Collectors.toList());
    }

    @Override
    public HireRecord findById(String hireRecordId) {
        List<HireRecord> hireRecordList = HireRecordDatabase.getInstance().getHireRecordList();

        return hireRecordList.stream()
                .filter( hireRecord -> hireRecord.getHireId().equals(hireRecordId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<HireRecord> findByBicycle(Bicycle bicycle) {
        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(hireRecord -> hireRecord.getBicycleHired().equals(bicycle))
                .collect(Collectors.toList());
    }

    @Override
    public List<HireRecord> findByCustomer(Customer customer) {
        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(hireRecord -> hireRecord.getCustomerHiring().equals(customer))
                .collect(Collectors.toList());
    }

    @Override
    public List<HireRecord> findByHireDate(Date hireDate) {
        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(hireRecord -> hireRecord.getStartDate().equals(hireDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<HireRecord> findByReturnDate(Date returnDate) {
        return HireRecordDatabase.getInstance()
                .getHireRecordList()
                .stream()
                .filter(hireRecord -> hireRecord.getReturnDate().equals(returnDate))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertHireRecord(HireRecord hireRecord) {
        HireRecordDatabase database = HireRecordDatabase.getInstance();
        database.addHireRecord(hireRecord);

        return true;
    }

    @Override
    public void updateHireRecord(HireRecord hireRecord) {
        HireRecordDatabase database = HireRecordDatabase.getInstance();
        int index = database.getHireRecordList().indexOf(hireRecord);

        database.getInstance()
                .getHireRecordList()
                .set(index, hireRecord);
    }

    @Override
    public boolean deleteHireRecord(HireRecord hireRecord) {
        HireRecordDatabase.getInstance()
                .getHireRecordList()
                .remove(hireRecord);

        return true;
    }
}
