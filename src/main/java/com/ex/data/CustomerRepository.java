package com.ex.data;

import com.ex.models.Customer;

public interface CustomerRepository extends Repository<Integer, Customer> {
    Customer findByEmail(String email);
}
