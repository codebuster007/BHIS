package com.bhis.service;

import com.bhis.dao.CustomerDao;
import com.bhis.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerRepo;

    public CustomerServiceImpl(CustomerDao customerRepo){
        this.customerRepo= customerRepo;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepo.findById(customerId);
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        return customerRepo.findByName(name);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepo.insertCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepo.updateCustomer(customer);
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        return customerRepo.deleteCustomer(customer);
    }
}
