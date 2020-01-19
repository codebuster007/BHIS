package com.bhis.service;

import com.bhis.dao.PaymentDao;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.Payment;

import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDao.findAll();
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        return paymentDao.findById(paymentId);
    }

    @Override
    public List<Payment> getPaymentByCustomer(Customer customer) {
        return paymentDao.findByCustomer(customer);
    }

    @Override
    public List<Payment> getPaymentByBicycle(Bicycle bicycle) {
        return paymentDao.findByBicycle(bicycle);
    }

    @Override
    public List<Payment> getPaymentByDate(Date payDate) {
        return paymentDao.findByDate(payDate);
    }

    @Override
    public boolean addPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDao.updatePayment(payment);
    }

    @Override
    public boolean removePayment(Payment payment) {
        return paymentDao.deletePayment(payment);
    }
}
