package com.bhis.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Accessors(chain = true)
public class Payment implements Serializable{

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 5545483345676547559L;

    @Setter(AccessLevel.NONE)
    private String paymentId;

    @Getter(AccessLevel.NONE)
    private HireRecord transaction;

    @Setter(AccessLevel.NONE)
    private double totalAmountPaid;

    @Setter(AccessLevel.NONE)
    private Date payDate;

    private void setPaymentId(){
        this.paymentId = transaction.getHireId();
    }

    public Customer getCustomer(){
        return transaction.getCustomerHiring();
    }

    public Bicycle getBicycle(){
        return transaction.getBicycleHired();
    }

    public double getAmountDue(){
        return transaction.getAmountDue();
    }

    public double getAmountHoursLate(){
        return transaction.getAmountHoursLate();
    }

    public int getHoursLate(){
        return transaction.getHoursLate();
    }

    public String getHireDuration(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date hireDate = transaction.getStartDate();
        Date returnDate = transaction.getReturnDate();

        long diffInMillis = Math.abs(returnDate.getTime() - hireDate.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if(diff < 24){
            return String.format("%s Hours", diff);
        }

        diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return String.format("%s %s", diff, diff > 1 ? "Days" : "Day");
    }

    private void setPayDate(){
        payDate = transaction.getStartDate();
    }
    /**
     * set the value of the total amount paid for a hire transaction
     */
    private void setTotalAmountPaid(){
        totalAmountPaid = getAmountDue() + getAmountHoursLate();
    }

    public double getTotalAmountPaid(){
        setTotalAmountPaid();

        return totalAmountPaid;
    }
}
