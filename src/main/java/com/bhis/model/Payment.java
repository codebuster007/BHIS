package com.bhis.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Payment implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -3019238620570352574L;

    private Date payDate;
    private HireRecord payment;

    @Setter(AccessLevel.NONE)
    private double totalAmountPaid;


    /**
     * set the value of the total amount paid for a hire transaction
     */
    private void setTotalAmountPaid(){
        totalAmountPaid = payment.getAmountDue() + payment.getAmountHoursLate();
    }

    public double getTotalAmountPaid(){
        setTotalAmountPaid();

        return totalAmountPaid;
    }
}
