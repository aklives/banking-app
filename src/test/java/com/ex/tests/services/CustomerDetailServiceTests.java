package com.ex.tests.services;

import com.ex.data.CustomerRepository;
import com.ex.data.dao.CustomerRepositoryImpl;
import com.ex.models.Customer;
import com.ex.services.CustomerDetailService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CustomerDetailServiceTests {
    private CustomerRepository mockRepository;
    private CustomerDetailService customerDetailService;

    private Customer goodCustomer = new Customer(10, "John", "Doe", "john.doe@gmail.com", "123");

    @Before
    public void beforeEach() {
        mockRepository = Mockito.mock(CustomerRepository.class);
        customerDetailService = new CustomerDetailService();
        customerDetailService.setCustomerRepository(mockRepository);
    }

    @Test
    public void shouldReturnNullForEmptyEmailString() {
        Mockito.when(mockRepository.findByEmail("")).thenReturn(null);
        Customer actual = customerDetailService.getCustomerByEmail("");
        Assert.assertNull("Null customer expected got not null", actual);
    }

    @Test
    public void shouldReturnGoodCustomer(){
        Mockito.when(mockRepository.findByEmail(goodCustomer.getEmail())).thenReturn(goodCustomer);
        Customer actual = customerDetailService.getCustomerByEmail("john.doe@gmail.com");
        Assert.assertNotNull("Null customer received", actual);
    }
}
