package com.bhis.dao;

import com.bhis.model.Customer;

import java.util.List;


/**
 * This can be replaced with a JPA Repository class
 *
 */
public interface CustomerDao {

    List<Customer> findAll();
    Customer findById(String customerId);
    List<Customer> findByName(String name);
    boolean insertCustomer(Customer customer);
    void updateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);

}
