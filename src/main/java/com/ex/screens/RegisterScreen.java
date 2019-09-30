package com.ex.screens;

import com.ex.data.CustomerRepository;
import com.ex.models.Customer;
import com.ex.platform.Screen;
import com.ex.platform.StringMenuBuilder;
import com.ex.utils.ConnectionManager;
import com.ex.utils.PostgresConnectionManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class RegisterScreen implements Screen {
    CustomerRepository cRepo;
    public static ConnectionManager manager;

    public RegisterScreen(CustomerRepository cRepo) {
        this.cRepo = cRepo;
    }

    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        Screen screen = null;

        System.out.println(("Please input your First Name: "));
        String firstName = scanner.next();

        System.out.println("Please input your Last Name: ");
        String lastName = scanner.next();

        System.out.println("Please enter your email");
        String email = scanner.next();

        System.out.println("Please enter your password");
        String password = scanner.next();

        System.out.println("Thank you for registering with Revature Bank");

        Customer customer = new Customer(0, firstName, lastName, email, password);

        System.out.println();

        int newId = cRepo.save(customer);
        if (newId > 0) {
            customer.setId(newId);
//            System.out.println(customer);
        }
        System.out.println();

        screen = new LoginScreen(cRepo);

        return screen;
    }
}


