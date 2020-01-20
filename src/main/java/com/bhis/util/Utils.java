package com.bhis.util;

import com.bhis.database.BicycleDatabase;
import com.bhis.database.CustomerDatabase;
import com.bhis.model.Bicycle;
import com.bhis.model.Customer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.bhis.model.Bicycle.BicycleSize.*;
import static com.bhis.model.Bicycle.BicycleType.*;
import static com.bhis.model.Customer.Gender.FEMALE;
import static com.bhis.model.Customer.Gender.MALE;

public class Utils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int ID_LENGTH = 6;
    public static final String CUSTOMER_PREPEND = "CUST";
    public static final String BIKE_PREPEND = "BIKE";
    public static final String HIRE_PREPEND = "HIRE";


    public static String generateRandomString(String prepend) {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        returnValue.append(prepend).append("-");

        IntStream.range(0, ID_LENGTH).mapToObj(n -> ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }

    public static String generateRandomInteger(String prepend) {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        if(!prepend.isEmpty()){
            returnValue.append(prepend).append("-");
        }

        IntStream.range(0, ID_LENGTH).mapToObj(n -> NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }


    public static void generateBicycleDummyData(){
        List<Bicycle> bicycleList = new ArrayList<>();
        bicycleList.add(new Bicycle()
                .setBicycleImage("/images/bicycle_img.png")
            .setBicycleMake("Tesla")
            .setBicycleSize(Arrays.asList(S))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
            .setBicycleType(TANDEM_BIKE)
            .setBicycleQuantity(10)
            .setBicycleModel("C-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(XL))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
                .setBicycleType(SPORTY_MOUNTAIN_BIKE)
                .setBicycleQuantity(10)
                .setBicycleModel("Z-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
                .setBicycleType(STANDARD_HYBRID_BIKE)
                .setBicycleQuantity(10)
                .setBicycleModel("X-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M, L))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
                .setBicycleType(TANDEM_BIKE)
                .setBicycleQuantity(10)
                .setBicycleModel("E-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M, XL))
                .setBicycleColor(Arrays.asList("BLUE", "PINK"))
                .setBicycleType(KIDS_BIKE)
                .setBicycleQuantity(0)
                .setBicycleModel("A-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList(S, M))
                .setBicycleColor(Arrays.asList("PURPLE", "BLUE", "BLACK"))
                .setBicycleType(ADULT_ELECTRIC_BIKE)
                .setBicycleQuantity(10)
                .setBicycleModel("F-Class Grade"));

        bicycleList.add(new Bicycle()
                .setBicycleNo(Utils.generateRandomInteger(BIKE_PREPEND))
                .setBicycleMake("BMW")
                .setBicycleImage("/images/bicycle_img.png")
                .setBicycleSize(Arrays.asList( M, XL))
                .setBicycleColor(Arrays.asList("BLUE", "PINK"))
                .setBicycleType(KIDS_BIKE)
                .setBicycleQuantity(0)
                .setBicycleModel("A-Class Grade"));

        BicycleDatabase.getInstance().setBicycleList(bicycleList);
    }

    public static void generateCustomerDummyData(){

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
