package com.bhis.database;

import com.bhis.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton that will hold customers data
 */
public class CustomerDatabase {

    private static CustomerDatabase mInstance;

    @Getter
    @Setter
    private List<Customer> customerList = null;

    public static CustomerDatabase getInstance(){
        if(mInstance == null)
            mInstance = new CustomerDatabase();

        return mInstance;
    }

    private CustomerDatabase(){
        customerList = new ArrayList<>();
    }

    public boolean addCustomer(Customer customer){
        if(!customerList.contains(customer)) {
            customerList.add(customer);

            return true;
        }
        return false;
    }
}
