package com.bhis.service;

import com.bhis.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(String customerId);
    List<Customer> getCustomerByName(String name);
    boolean addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    boolean removeCustomer(Customer customer);
}
