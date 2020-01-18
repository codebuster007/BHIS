package com.bhis.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Customer implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 4574063963292845170L;


    private String customerId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String emailAddress;
    private String homeAddress;


    @Getter(AccessLevel.NONE)
    public enum Gender{
        MALE,
        FEMALE
    }
}
