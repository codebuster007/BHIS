package com.bhis.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
    private List<String> bicycleColor;
    private String bicycleImage;
    private List<BicycleSize> bicycleSize;
    private int bicycleQuantity;
    private double bicycleDeposit;
    private double bicycleHourlyRate;
    private double bicycleDailyRate;
    private double lateHourlyRate; // late return hourly rate


    @Getter(AccessLevel.NONE)
    @RequiredArgsConstructor
    public enum BicycleType{
        STANDARD_HYBRID_BIKE("STANDARD HYBRID"),
        SPORTY_MOUNTAIN_BIKE("SPORTY MOUNTAIN"),
        ADULT_ELECTRIC_BIKE("ADULT ELECTRIC"),
        KIDS_BIKE("KIDS"),
        TANDEM_BIKE("TANDEM");

        @Getter
        @NonNull private String bicycleType;
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
