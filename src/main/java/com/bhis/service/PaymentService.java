package com.bhis.service;

import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(String paymentId);

    List<Payment> getPaymentByCustomer(Customer customer);
    List<Payment> getPaymentByBicycle(Bicycle bicycle);
    List<Payment> getPaymentByDate(Date payDate);

    boolean addPayment(Payment payment);
    void updatePayment(Payment payment);
    boolean removePayment(Payment payment);

}
