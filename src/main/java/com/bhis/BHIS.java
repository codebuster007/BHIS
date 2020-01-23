package com.bhis;

import com.bhis.controllers.MainController;
import com.bhis.dao.BicycleDaoImpl;
import com.bhis.dao.CustomerDaoImpl;
import com.bhis.dao.HireRecordDaoImpl;
import com.bhis.dao.PaymentDaoImpl;
import com.bhis.database.CustomerDatabase;
import com.bhis.database.HireRecordDatabase;
import com.bhis.model.Customer;
import com.bhis.service.*;
import com.bhis.util.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bhis.model.Customer.Gender.FEMALE;
import static com.bhis.model.Customer.Gender.MALE;
import static com.bhis.util.Utils.CUSTOMER_PREPEND;

public class BHIS extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Utils.generateBicycleDummyData();
        Utils.generateCustomerDummyData();
        Utils.generateHireRecordDummyData();
        Utils.generatePaymentDummyData();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainPage.fxml"));
//        loader.setLocation();

        MainController mainController = new MainController();
        mainController.initData(new HireRecordServiceImpl(new HireRecordDaoImpl()),
                new PaymentServiceImpl(new PaymentDaoImpl()),
                new BicycleServiceImpl(new BicycleDaoImpl()));

        loader.setController(mainController);

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));

        primaryStage.show();

//        dummyCustomerTest();
//        dummyHireRecordTest();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void dummyHireRecordTest() {
        Utils.generateHireRecordDummyData();

        printHireRecordDb();


    }

    public void dummyCustomerTest() {
        Utils.generateCustomerDummyData();


        // Printing test data
        printCustomerDb();
        System.out.println("\n");

        // testing CustomerService Functionality;
        CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

        // testing get all customer
        List<Customer>  customerList = customerService.getAllCustomers();
        System.out.println("Testing customer service getAllCustomers");
        printDatabase(customerList);


        // testing adding customer
        Customer newCustomer = new Customer()
                .setCustomerId(Utils.generateRandomString(CUSTOMER_PREPEND))
                .setFirstName("Baby")
                .setLastName("Shark")
                .setGender(MALE)
                .setEmailAddress("babyshark@bickers.io")
                .setHomeAddress("No 2 Lorem ipsum street bikini bottom Ikeja");


        customerService.addCustomer(newCustomer);

        System.out.println("After Adding new Customer");
        printCustomerDb();


        // testing findCustomerById
        Customer foundCustomer = customerService.getCustomerById(customerList.get(0).getCustomerId());
        System.out.println("After findCustomerById: " + foundCustomer);

        Customer foundCustomer2 = customerService.getCustomerById("CUST-95bhf2");
        System.out.println("After findCustomerById with wrong Id: " + foundCustomer2);

        System.out.println("\n");


        // testing findCustomerByName
        List<Customer> customerByName = customerService.getCustomerByName(customerList.get(2).getFirstName());
        System.out.println("After findCustomerByName: ");
        printDatabase(customerByName);
        System.out.println("\n");

        List<Customer> customerByName2 = customerService.getCustomerByName("David Beckham");
        System.out.println("After findCustomerById with wrong name: ");
        printDatabase(customerByName2);
        System.out.println("\n");


        //testing updateCustomer
        Customer updateCustomer = customerList.get(3);
        updateCustomer.setGender(FEMALE);
        customerService.updateCustomer(updateCustomer);
        System.out.println("After Update Customer");
        printCustomerDb();

        System.out.println("\n");


        // testing removeCustomer
        Customer removeCustomer = customerList.get(4);
        customerService.removeCustomer(removeCustomer);
        System.out.println("After Remove Customer");
        printCustomerDb();

    }

    public void printCustomerDb() {
        CustomerDatabase.getInstance()
                .getCustomerList()
                .forEach(System.out::println);

        System.out.println("\n");
    }

    public void printHireRecordDb() {
        AtomicInteger i = new AtomicInteger(1);
        HireRecordDatabase.getInstance()
                .getHireRecordList()
                .forEach(hireRecord -> {

                    if (hireRecord.getHireDuration().contains("1 Day"))
                        hireRecord.setBicycleReturned(true);
//                        else if(hireRecord.getHireDuration().contains("Days"))
//                            hireRecord.setHoursLateTest(LocalDateTime.now().plusDays(2).plusHours(1));
//                        else
//                            hireRecord.setHoursLateTest(LocalDateTime.now().plusHours(8));

                    System.out.println(hireRecord);
                });

        System.out.println("\n");
    }

    public <T> void printDatabase(List<T> list) {
        if (list != null) {
            list.forEach(System.out::println);
            System.out.println("\n");
            return;
        }
    }
}
