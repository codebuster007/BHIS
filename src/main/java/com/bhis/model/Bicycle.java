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
    private BikeType bicycleType;
    private String bicycleColor;
    private BikeSize bicycleSize;
    private int bicycleQuantity;
    private double bicycleDeposit;
    private double bicycleHourlyRate;
    private double bicycleDailyRate;


    @Getter(AccessLevel.NONE)
    public enum BikeType{
        STANDARD_HYBRID_BIKE,
        SPORTY_MOUNTAIN_BIKE,
        ADULT_ELECTRIC_BIKE,
        KIDS_BIKE,
        TANDEM_BIKE
    }

    @Getter(AccessLevel.NONE)
    @RequiredArgsConstructor
    public enum BikeSize{

        S("SMALL"),
        M("MEDIUM"),
        L("LARGE"),
        XL("eXtra Large");

        @Getter
        @NonNull private String bicycleSize;
    }
}
