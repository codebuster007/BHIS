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
public class HireRecord implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -3446042244049078080L;

    private String hireId;
    private Bicycle bicycleHired;
    private Customer customerHiring;
    private Date startDate;
    private Date returnDate;
    private double amountDue;
    private int hoursLate; // number of hours late
    private boolean bicycleReturned = true;

    @Setter(AccessLevel.NONE)
    private double amountHoursLate;



    /**
     * set the amount to be paid for returning the bicycle
     * later than subscribed
     */
    private void setAmountHoursLate(){
        amountHoursLate = hoursLate * bicycleHired.getLateHourlyRate();
    }

    public double getAmountHoursLate(){
        setAmountHoursLate();

        return this.amountHoursLate;
    }
}
