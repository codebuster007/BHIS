package com.bhis.service;

import com.bhis.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer findCustomerById(String customerId);
    List<Customer> findCustomerByName(String name);
    boolean addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    boolean removeCustomer(Customer customer);
}
