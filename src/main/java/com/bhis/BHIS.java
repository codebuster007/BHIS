package com.bhis;

import com.bhis.dao.CustomerDaoImpl;
import com.bhis.database.CustomerDatabase;
import com.bhis.model.Customer;
import com.bhis.service.CustomerService;
import com.bhis.service.CustomerServiceImpl;
import com.bhis.util.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import static com.bhis.model.Customer.Gender.FEMALE;
import static com.bhis.model.Customer.Gender.MALE;
import static com.bhis.util.Utils.CUSTOMER_PREPEND;

public class BHIS extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/sample.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();

        dummyCustomerTest();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void dummyCustomerTest(){
        Utils.generateDummyData();


        // Printing test data
        printDatabase(null);
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
        printDatabase(null);


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
        printDatabase(null);

        System.out.println("\n");


        // testing removeCustomer
        Customer removeCustomer = customerList.get(4);
        customerService.removeCustomer(removeCustomer);
        System.out.println("After Remove Customer");
        printDatabase(null);

    }


    public void printDatabase(List<Customer> customers){
        if(customers != null){
            customers.forEach(System.out::println);
            System.out.println("\n");
            return;
        }

        CustomerDatabase.getInstance()
                .getCustomerList()
                .forEach(System.out::println);

        System.out.println("\n");
    }
}
