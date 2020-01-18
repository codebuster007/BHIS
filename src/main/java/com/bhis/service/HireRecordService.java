package com.bhis.service;

import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.HireRecord;

import java.util.Date;
import java.util.List;

public interface HireRecordService {

    List<HireRecord> getAllHireRecords();
    List<HireRecord> getAllReturnedHireRecords();
    List<HireRecord> getAllHiredHireRecords();

    HireRecord getHireRecordById(String hireRecordId);

    List<HireRecord> getHireRecordByBicycle(Bicycle bicycle);
    List<HireRecord> getHireRecordByCustomer(Customer customer);
    List<HireRecord> getHireRecordByHireDate(Date hireDate);
    List<HireRecord> getHireRecordByReturnDate(Date returnDate);

    boolean addHireRecord(HireRecord hireRecord);
    void updateHireRecord(HireRecord hireRecord);
    boolean removeHireRecord(HireRecord hireRecord);
    
}
