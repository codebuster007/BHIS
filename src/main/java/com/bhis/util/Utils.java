package com.bhis.util;

import com.bhis.database.CustomerDatabase;
import com.bhis.model.Customer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.bhis.model.Customer.Gender.FEMALE;
import static com.bhis.model.Customer.Gender.MALE;

public class Utils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int ID_LENGTH = 6;
    public static final String CUSTOMER_PREPEND = "CUST";
    public static final String BIKE_PREPEND = "BIKE";
    public static final String HIRE_TRANSACTION_PREPEND = "HTRN";


    public static String generateRandomString(String prepend) {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        returnValue.append(prepend).append("-");

        IntStream.range(0, ID_LENGTH).mapToObj(n -> ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }

    public static String generateRandomInteger() {
        StringBuilder returnValue = new StringBuilder(ID_LENGTH);

        IntStream.range(0, ID_LENGTH).mapToObj(n -> NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length()))).forEach(returnValue::append);

        return new String(returnValue);
    }

    public static void generateDummyData(){

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
