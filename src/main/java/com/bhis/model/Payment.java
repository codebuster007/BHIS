package com.bhis.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Payment extends RecursiveTreeObject<Payment> implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 5545483345676547559L;

    @Setter(AccessLevel.NONE)
    private StringProperty paymentId;

    @Getter(AccessLevel.NONE)
    private Property<HireRecord> transaction;

    @Setter(AccessLevel.NONE)
    private Property<Double> totalAmountPaid;

    @Setter(AccessLevel.NONE)
    private Property<Double> totalAmountPaidNoDeposit;

    @Setter(AccessLevel.NONE)
    private Property<LocalDateTime> payDate;

    public Payment() {
        paymentId = new SimpleStringProperty();
        transaction = new SimpleObjectProperty<>();
        totalAmountPaid = new SimpleObjectProperty<>();
        totalAmountPaidNoDeposit = new SimpleObjectProperty<>();
        payDate = new SimpleObjectProperty<>();
    }

    private void setPaymentId() {
        this.paymentId.set(transaction.getValue().getHireId());
    }

    public String getPaymentId() {
        setPaymentId();

        return paymentId.get();
    }

    public Payment setTransaction(HireRecord transaction) {
        this.transaction.setValue(transaction);
        return this;
    }

    public StringProperty paymentIdProperty() {
        return paymentId;
    }

    public Customer getCustomer() {
        return transaction.getValue().getCustomerHiring();
    }

    public Property<String> customerIdProperty() {
        return new SimpleObjectProperty<>(transaction.getValue().getCustomerHiring().getCustomerId());
    }

    public Property<String> customerNameProperty() {
        return new SimpleObjectProperty<>(transaction.getValue().getCustomerHiring().getFirstName() + " "
                + transaction.getValue().getCustomerHiring().getLastName());
    }


    public Bicycle getBicycle() {
        return transaction.getValue().getBicycleHired();
    }

    public Property<String> bicycleIdProperty() {
        return new SimpleObjectProperty<>(transaction.getValue().getBicycleHired().getBicycleNo());
    }

    public Property<String> bicycleTypeProperty() {
        return new SimpleObjectProperty<>(transaction.getValue().getBicycleHired().getBicycleType().getBicycleType());
    }

    public double getAmountDue() {
        return transaction.getValue().getAmountDue();
    }

    public DoubleProperty amountDueProperty() {
        return new SimpleDoubleProperty(transaction.getValue().getAmountDue());
    }

    public double getDeposit() {
        return transaction.getValue().getBicycleDeposit();
    }

    public DoubleProperty depositProperty() {
        return new SimpleDoubleProperty(transaction.getValue().getBicycleDeposit());
    }

    public double getAmountHoursLate() {
        return transaction.getValue().getAmountHoursLate();
    }

    public DoubleProperty amountHoursLateProperty() {
        return new SimpleDoubleProperty(transaction.getValue().getAmountHoursLate());
    }

    public int getHoursLate() {
        return transaction.getValue().getHoursLate();
    }

    public IntegerProperty hoursLateProperty() {
        return new SimpleIntegerProperty(transaction.getValue().getHoursLate());
    }

    public String getHireDuration() {

        return transaction.getValue().getHireDuration();
    }

    public StringProperty hireDurationProperty() {
        return new SimpleStringProperty(transaction.getValue().getHireDuration());
    }

    private void setPayDate() {
        payDate.setValue(transaction.getValue().getStartDate());
    }

    public LocalDateTime getPayDate() {
        setPayDate();

        return payDate.getValue();
    }

    public StringProperty payDateProperty() {
        return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a ").format(payDate.getValue()));
    }

    /**
     * set the value of the total amount paid for a hire transaction
     */
    private void setTotalAmountPaid() {
        totalAmountPaid.setValue(getAmountDue() + getAmountHoursLate());
    }

    public double getTotalAmountPaid() {
        setTotalAmountPaid();

        return totalAmountPaid.getValue();
    }

    public Property<Double> totalAmountPaidProperty() {
        return totalAmountPaid;
    }

    private void setTotalAmountPaidNoDeposit() {
        if (transaction.getValue().isDepositRefunded())
            totalAmountPaidNoDeposit.setValue(getTotalAmountPaid() - getDeposit());
        totalAmountPaidNoDeposit.setValue(getTotalAmountPaid());
    }

    public double getTotalAmountPaidNoDeposit() {
        setTotalAmountPaidNoDeposit();

        return totalAmountPaidNoDeposit.getValue();
    }

    public Property<Double> totalAmountPaidNoDepositProperty() {
        return totalAmountPaidNoDeposit;
    }
}
