package com.bhis.dao;


import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentDao {

    List<Payment> findAll();

    Payment findById(String paymentId);

    List<Payment> findByCustomer(Customer customer);
    List<Payment> findByBicycle(Bicycle bicycle);
    List<Payment> findByDate(Date payDate);

    boolean insertPayment(Payment payment);
    void updatePayment(Payment payment);
    boolean deletePayment(Payment payment);
}
