package com.bhis.dao;

import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.HireRecord;

import java.util.Date;
import java.util.List;

public interface HireRecordDao {

    List<HireRecord> findAll();
    List<HireRecord> findAllReturned();
    List<HireRecord> findAllHired();

    HireRecord findById(String hireRecordId);

    List<HireRecord> findByBicycle(Bicycle bicycle);
    List<HireRecord> findByCustomer(Customer customer);
    List<HireRecord> findByHireDate(Date hireDate);
    List<HireRecord> findByReturnDate(Date returnDate);

    boolean insertHireRecord(HireRecord hireRecord);
    void updateHireRecord(HireRecord hireRecord);
    boolean deleteHireRecord(HireRecord hireRecord);

}
