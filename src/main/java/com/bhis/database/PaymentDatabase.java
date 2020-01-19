package com.bhis.database;

import com.bhis.model.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PaymentDatabase {
    private static PaymentDatabase mInstance;

    @Getter
    @Setter
    private List<Payment> paymentList = null;

    public static PaymentDatabase getInstance(){
        if(mInstance == null)
            mInstance = new PaymentDatabase();

        return mInstance;
    }

    private PaymentDatabase(){
        paymentList = new ArrayList<>();
    }

    public boolean addPayment(Payment payment){
        if(!paymentList.contains(payment)) {
            paymentList.add(payment);
            return true;
        }
        return false;
    }
}
