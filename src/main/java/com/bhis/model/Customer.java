package com.bhis.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class Customer extends RecursiveTreeObject<Customer> implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 4574063963292845170L;


    private StringProperty customerId;
    private StringProperty firstName;
    private StringProperty lastName;
    private Property<Gender> gender;
    private StringProperty emailAddress;
    private StringProperty homeAddress;

    public Customer() {
        customerId = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        gender = new SimpleObjectProperty<>();
        emailAddress = new SimpleStringProperty();
        homeAddress = new SimpleStringProperty();
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public Customer setCustomerId(String Id) {
        this.customerId.set(Id);
        return this;
    }

    public StringProperty customerIdProperty() {
        return customerId;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public Customer setFirstName(String firstName) {
        this.firstName.set(firstName);
        return this;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public Customer setLastName(String lastName) {
        this.lastName.set(lastName);
        return this;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public Property<Gender> genderProperty() {
        return gender;
    }

    public Gender getGender() {
        return gender.getValue();
    }

    public Customer setGender(Gender gender) {
        this.gender.setValue(gender);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public Customer setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
        return this;
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public Customer setHomeAddress(String homeAddress) {
        this.homeAddress.set(homeAddress);
        return this;
    }

    public StringProperty homeAddressProperty() {
        return homeAddress;
    }

    @Getter(AccessLevel.NONE)
    public enum Gender {
        MALE,
        FEMALE
    }
}
