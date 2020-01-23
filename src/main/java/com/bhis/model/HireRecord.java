package com.bhis.model;

import com.bhis.util.Utils;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class HireRecord implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -3446042244049078080L;

    private String hireId;
    private Bicycle bicycleHired;
    private Customer customerHiring;
    private LocalDateTime startDate;
    private LocalDateTime returnDate;
    private HireStatus hireStatus;
    private boolean depositRefunded = false;
    private boolean bicycleReturned = false;

    @Setter(AccessLevel.NONE)
    private String hireDuration;

    @Setter(AccessLevel.NONE)
    private double amountDue;

    @Setter(AccessLevel.NONE)
    private int hoursLate; // number of hours late

    @Setter(AccessLevel.NONE)
    private double amountHoursLate;

    private void setHireDuration() {
        // 30minutes extra added , sort of like a head start
        long diffInHours = Math.abs(Utils.getDateInHours(startDate, returnDate.plusMinutes(30)));
        if (diffInHours < 24) {
            hireDuration = String.format("%s Hours", diffInHours);
            return;
        }

        // 30minutes extra added , sort of like a head start
        long diffInDays = Utils.getDateInDays(startDate, returnDate.plusMinutes(30));
        hireDuration = String.format("%s %s", diffInDays, diffInDays > 1 ? "Days" : "Day");
    }

    public String getHireDuration() {

        setHireDuration();

        return hireDuration;
    }

    private void setAmountDue() {

        amountDue = Utils.round(getDue(getBicycleHired().getLateHourlyRate(),
                getBicycleHired().getBicycleDailyRate()) + bicycleHired.getBicycleDeposit(), 2);
    }

    public double getAmountDue() {
        setAmountDue();

        return amountDue;
    }

    // util method
    private double getDue(double hourRate, double dayRate) {

        long diffInHours = Math.abs(Utils.getDateInHours(startDate, returnDate));
        if (diffInHours < 24)
            return Math.toIntExact(diffInHours) * hourRate;

        long diffInDays = Utils.getDateInDays(startDate, returnDate);
        return Math.toIntExact(diffInDays) * dayRate;

    }

    /**
     * set the amount to be paid for returning the bicycle
     * later than subscribed
     */
    private void setAmountHoursLate() {
        amountHoursLate = Utils.round(getHoursLate() * bicycleHired.getLateHourlyRate(), 2);
    }

    public double getAmountHoursLate() {
        setAmountHoursLate();

        return this.amountHoursLate;
    }

    private void setHoursLate() {
        hoursLate = Math.toIntExact(Utils.getDateInHours(getReturnDate(), LocalDateTime.now()));
    }

    // TODO: Remove this method
//    public void setHoursLateTest(LocalDateTime test){
//        hoursLate = Math.toIntExact(Utils.getDateInHours(getReturnDate(), test));
//    }

    public int getHoursLate() {
        setHoursLate();

        return hoursLate <= 0 ? 0 : hoursLate;
    }

    public void setBicycleReturned(boolean returned) {
//        if(getHoursLate() == 0)
//            depositRefunded = true;
        bicycleReturned = returned;
    }

    public double getBicycleDeposit() {
        return bicycleHired.getBicycleDeposit();
    }

    @Override
    public String toString() {
        return "HireRecord{" +
                "hireId='" + getHireId() + '\'' +
                ", bicycleHired=" + getBicycleHired() +
                ", customerHiring=" + getCustomerHiring() +
                ", startDate=" + getStartDate() +
                ", returnDate=" + getReturnDate() +
                ", depositRefunded=" + isDepositRefunded() +
                ", bicycleReturned=" + isBicycleReturned() +
                ", hireDuration='" + getHireDuration() + '\'' +
                ", amountDue=" + getAmountDue() +
                ", hoursLate=" + getHoursLate() +
                ", amountHoursLate=" + getAmountHoursLate() +
                '}';
    }

    @Getter(AccessLevel.NONE)
    @RequiredArgsConstructor
    public enum HireStatus {

        STALE("STALE"),
        ONGOING("Ongoing..."),
        COMPLETED("Completed");

        @NonNull
        private String hireStatus;
    }
}
