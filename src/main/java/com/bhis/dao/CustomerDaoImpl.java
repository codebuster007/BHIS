package com.bhis.dao;

import com.bhis.database.CustomerDatabase;
import com.bhis.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An implementation of a CustomerDao which simulates a JPA Repository
 * This can be removed after database functionality has been added
 */
public class CustomerDaoImpl implements CustomerDao{

    @Override
    public List<Customer> findAll() {
        return CustomerDatabase.getInstance()
                .getCustomerList();
    }

    @Override
    public Customer findById(String customerId) {
        List<Customer> customerList = CustomerDatabase.getInstance().getCustomerList();

        return customerList.stream()
                .filter( cus -> cus.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customerList = CustomerDatabase.getInstance().getCustomerList();

        return customerList.stream()
                .filter( cus -> cus.getFirstName().equals(name) || cus.getLastName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        CustomerDatabase database = CustomerDatabase.getInstance();
        database.addCustomer(customer);

        return true;
    }

    @Override
    public void updateCustomer(Customer customer) {
        CustomerDatabase database = CustomerDatabase.getInstance();
        int index = database.getCustomerList().indexOf(customer);

        database.getInstance()
                .getCustomerList()
                .set(index, customer);

    }

    @Override
    public boolean deleteCustomer(Customer customer) {

        CustomerDatabase.getInstance().getCustomerList().remove(customer);

        return true;
    }
}
