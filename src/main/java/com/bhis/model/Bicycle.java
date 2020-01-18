package com.bhis.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Bicycle implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -675735530183914457L;

    private String bicycleNo;
    private String bicycleMake;
    private String bicycleModel;
    private BicycleType bicycleType;
    private String bicycleColor;
    private BicycleSize bicycleSize;
    private int bicycleQuantity;
    private double bicycleDeposit;
    private double bicycleHourlyRate;
    private double bicycleDailyRate;
    private double lateHourlyRate; // late return hourly rate


    @Getter(AccessLevel.NONE)
    public enum BicycleType{
        STANDARD_HYBRID_BIKE,
        SPORTY_MOUNTAIN_BIKE,
        ADULT_ELECTRIC_BIKE,
        KIDS_BIKE,
        TANDEM_BIKE
    }

    @Getter(AccessLevel.NONE)
    @RequiredArgsConstructor
    public enum BicycleSize{

        S("SMALL"),
        M("MEDIUM"),
        L("LARGE"),
        XL("eXtra Large");

        @Getter
        @NonNull private String bicycleSize;
    }
}
