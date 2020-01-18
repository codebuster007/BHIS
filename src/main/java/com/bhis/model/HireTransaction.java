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
public class HireTransaction implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -3446042244049078080L;

    private String hireTransactionId;
    private Bicycle bicycleHired;
    private Customer customerHiring;
    private Date startDate;
    private Date returnDate;
    private double amountDue;
    private int lateHour;
    private double amountLateHour;
}
