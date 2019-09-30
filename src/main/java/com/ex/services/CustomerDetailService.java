package com.ex.services;

import com.ex.data.CustomerRepository;
import com.ex.models.Customer;

public class CustomerDetailService {

    private CustomerRepository customerRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository =  customerRepository;
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
