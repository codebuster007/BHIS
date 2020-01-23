package com.bhis.util;

import com.bhis.database.BicycleDatabase;
import com.bhis.database.CustomerDatabase;
import com.bhis.database.HireRecordDatabase;
import com.bhis.database.PaymentDatabase;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;
import com.bhis.model.HireRecord;
import com.bhis.model.Payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.bhis.model.Bicycle.BicycleSize.*;
import static com.bhis.model.Bicycle.BicycleType.*;
import static com.bhis.model.Customer.Gender.FEMALE;
import static com.bhis.model.Customer.Gender.MALE;
import static com.bhis.model.HireRecord.HireStatus.ONGOING;

public class Utils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int ID_LENGTH = 6;
    public static final String CUSTOMER_PREPEND = "CUST";
    public static final String BIKE_PREPEND = "BIKE";
    public static final String HIRE_PREPEND = "HIRE";


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static long getDateInHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);

        return duration.toHours();
    }

    public static long getDateInDays(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);

        return Math.abs(duration.toDays());
    }

    public static String generateRandomString(String prepend) {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        returnValue.append(prepend).append("-");

        IntStream.range(0, ID_LENGTH).mapToObj(n -> ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }

    /**
     * Generating ID's for Bicycle and Hire Records
     *
     * @param prepend can be 'BIKE' or ''
     * @return the generated ID
     */
    public static String generateRandomInteger(String prepend) {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        if (!prepend.isEmpty()) {
            returnValue.append(prepend).append("-");
        }

        IntStream.range(0, ID_LENGTH).mapToObj(n -> NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }


    public static void generatePaymentDummyData() {
        List<Payment> paymentList = new ArrayList<>();

        List<HireRecord> hireRecords = HireRecordDatabase.getInstance().getHireRecordList();

        paymentList.add(new Payment().setTransaction(hireRecords.get(0)));
        paymentList.add(new Payment().setTransaction(hireRecords.get(1)));
        paymentList.add(new Payment().setTransaction(hireRecords.get(2)));
        paymentList.add(new Payment().setTransaction(hireRecords.get(3)));
        paymentList.add(new Payment().setTransaction(hireRecords.get(4)));

        PaymentDatabase.getInstance().setPaymentList(paymentList);
    }


    /**
     * Generate Dummy data for Hire Record Database
     */
    public static void generateHireRecordDummyData() {
        List<HireRecord> hireRecords = new ArrayList<>();

        List<Bicycle> bicycleList = BicycleDatabase.getInstance().getBicycleList();
        List<Customer> customerList = CustomerDatabase.getInstance().getCustomerList();

        hireRecords.add(new HireRecord()
                .setHireId(Utils.generateRandomInteger(HIRE_PREPEND))
                .setBicycleHired(bicycleList.get(0))
                .setCustomerHiring(customerList.get(0))
                .setStartDate(LocalDateTime.now())
                .setReturnDate(LocalDateTime.now().plusDays(2))
                .setHireStatus(ONGOING)
        );

        hireRecords.add(new HireRecord()
                .setHireId(Utils.generateRandomInteger(HIRE_PREPEND))
                .setBicycleHired(bicycleList.get(1))
                .setCustomerHiring(customerList.get(1))
                .setStartDate(LocalDateTime.now())
                .setReturnDate(LocalDateTime.now().plusDays(1))
                .setHireStatus(ONGOING)
        );

        hireRecords.add(new HireRecord()
                .setHireId(Utils.generateRandomInteger(HIRE_PREPEND))
                .setBicycleHired(bicycleList.get(2))
                .setCustomerHiring(customerList.get(2))
                .setStartDate(LocalDateTime.now())
                .setReturnDate(LocalDateTime.now().plusWeeks(4))
                .setHireStatus(ONGOING)
        );

        hireRecords.add(new HireRecord()
                .setHireId(Utils.generateRandomInteger(HIRE_PREPEND))
                .setBicycleHired(bicycleList.get(3))
                .setCustomerHiring(customerList.get(3))
                .setStartDate(LocalDateTime.now())
                .setReturnDate(LocalDateTime.now().plusHours(2))
                .setHireStatus(ONGOING)
        );

        hireRecords.add(new HireRecord()
                .setHireId(Utils.generateRandomInteger(HIRE_PREPEND))
                .setBicycleHired(bicycleList.get(4))
                .setCustomerHiring(customerList.get(4))
                .setStartDate(LocalDateTime.now())
                .setReturnDate(LocalDateTime.now().plusHours(3))
                .setHireStatus(ONGOING)
        );

        HireRecordDatabase.getInstance().setHireRecordList(hireRecords);
    }

    /**
     * Generate Dummy data for Bicycle Database
     */
    public static void generateBicycleDummyData() {
        List<Bicycle> bicycleList = new ArrayList<>();
//        bicycleList.add(new Bicycle()
//                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
//                .setBicycleImage("/images/bicycle_img.png")
//            .setBicycleMake("Tesla")
//            .setBicycleSize(Arrays.asList(S))
//                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
//            .setBicycleType(TANDEM_BIKE)
//            .setBicycleQuantity(10)
//            .setBicycleModel("C-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleHourlyRate(7.49)
                .setBicycleDailyRate(22.99)
                .setLateHourlyRate(1.99)
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(XL))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
                .setBicycleType(SPORTY_MOUNTAIN_BIKE)
                .setBicycleQuantity(10)
                .setBicycleModel("Z-Class Grade")
                .setBicycleDeposit(7.99));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("Tesla")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M))
                .setBicycleColor(Arrays.asList("PURPLE", "GREEN"))
                .setBicycleType(STANDARD_HYBRID_BIKE)
                .setBicycleHourlyRate(7.49)
                .setBicycleDailyRate(22.99)
                .setLateHourlyRate(1.99)
                .setBicycleQuantity(10)
                .setBicycleModel("X-Class Grade")
                .setBicycleDeposit(7.99));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("FORD")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M, L))
                .setBicycleColor(Arrays.asList("PURPLE", "RED"))
                .setBicycleType(TANDEM_BIKE)
                .setBicycleHourlyRate(14.96)
                .setBicycleDailyRate(39.99)
                .setLateHourlyRate(4.99)
                .setBicycleQuantity(0)
                .setBicycleModel("E-Class Grade")
                .setBicycleDeposit(14.99));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BENZ")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M, XL))
                .setBicycleColor(Arrays.asList("BLUE", "PINK"))
                .setBicycleType(KIDS_BIKE)
                .setBicycleHourlyRate(3.49)
                .setBicycleDailyRate(9.99)
                .setLateHourlyRate(0.99)
                .setBicycleQuantity(6)
                .setBicycleModel("A-Class Grade")
                .setBicycleDeposit(5.99));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("FERRARI")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M))
                .setBicycleColor(Arrays.asList("PURPLE", "BLACK"))
                .setBicycleType(ADULT_ELECTRIC_BIKE)
                .setBicycleHourlyRate(9.96)
                .setBicycleDailyRate(29.99)
                .setLateHourlyRate(2.99)
                .setBicycleQuantity(5)
                .setBicycleModel("F-Class Grade")
                .setBicycleDeposit(11.99));

//        bicycleList.add(new Bicycle()
//                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
//                .setBicycleMake("BMW")
//                .setBicycleImage("/images/bicycle_img.png")
//                .setBicycleSize(Arrays.asList( M, XL))
//                .setBicycleColor(Arrays.asList("BLUE", "PINK"))
//                .setBicycleType(KIDS_BIKE)
//                .setBicycleQuantity(0)
//                .setBicycleModel("A-Class Grade"));

        BicycleDatabase.getInstance().setBicycleList(bicycleList);
    }


    /**
     * Dummy Data for Customer database
     */
    public static void generateCustomerDummyData() {

        List<Customer> customerList = new ArrayList<>();

        //customer 1
        customerList.add(new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("John")
                .setLastName("Doe")
                .setGender(MALE)
                .setEmailAddress("johndoe@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street Lagos Ikeja")

        );

        // customer 2
        customerList.add(new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("David")
                .setLastName("Doe")
                .setGender(MALE)
                .setEmailAddress("daviddoe@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street Abuja FCT")

        );

        // customer 3
        customerList.add(new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("Sarah")
                .setLastName("Doe")
                .setGender(FEMALE)
                .setEmailAddress("sarahdoe@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street Lagos Ikeja")

        );

        // customer 4
        customerList.add(new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("Victor")
                .setLastName("Doe")
                .setGender(MALE)
                .setEmailAddress("victordoe@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street CA Ikeja")

        );

        // customer 5
        customerList.add(new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("Sylvia")
                .setLastName("Doe")
                .setGender(FEMALE)
                .setEmailAddress("sylviadoe@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street Lagos Ikeja")

        );


        CustomerDatabase.getInstance().setCustomerList(customerList);
    }

}
