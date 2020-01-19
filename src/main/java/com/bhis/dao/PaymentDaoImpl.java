package com.bhis.dao;

import com.bhis.database.PaymentDatabase;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.Payment;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentDaoImpl implements PaymentDao{
    @Override
    public List<Payment> findAll() {
        return PaymentDatabase.getInstance()
                .getPaymentList();
    }

    @Override
    public Payment findById(String paymentId) {
        List<Payment> paymentList = PaymentDatabase.getInstance().getPaymentList();

        return paymentList.stream()
                .filter( payment -> payment.getPaymentId().equals(paymentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Payment> findByCustomer(Customer customer) {
        List<Payment> paymentList = PaymentDatabase.getInstance().getPaymentList();

        return paymentList.stream()
                .filter( payment -> payment.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByBicycle(Bicycle bicycle) {
        List<Payment> paymentList = PaymentDatabase.getInstance().getPaymentList();

        return paymentList.stream()
                .filter( payment -> payment.getBicycle().equals(bicycle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByDate(Date payDate) {
        List<Payment> paymentList = PaymentDatabase.getInstance().getPaymentList();

        return paymentList.stream()
                .filter( payment -> payment.getPayDate().equals(payDate))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertPayment(Payment payment) {
        PaymentDatabase database = PaymentDatabase.getInstance();
        database.addPayment(payment);

        return true;
    }

    @Override
    public void updatePayment(Payment payment) {
        PaymentDatabase database = PaymentDatabase.getInstance();
        int index = database.getPaymentList().indexOf(payment);

        database.getInstance()
                .getPaymentList()
                .set(index, payment);
    }

    @Override
    public boolean deletePayment(Payment payment) {
        PaymentDatabase.getInstance()
                .getPaymentList()
                .remove(payment);

        return true;
    }
}
