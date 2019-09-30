package com.ex.screens;
import com.ex.data.CustomerRepository;
import com.ex.models.Customer;
import com.ex.platform.Screen;

import java.util.*;

public class LoginScreen implements Screen {
    CustomerRepository cRepo;

    public LoginScreen(CustomerRepository cRepo) {
        this.cRepo = cRepo;
    }


    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        Screen screen = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Login with Email.");
        String email = sc.next();
        Customer customer = cRepo.findByEmail(email);

        System.out.println("Please enter your password.");
        String password = sc.next();
//        System.out.println("Your password in object is " + customer.getPassword() + " and in method is " + password);

        if (password.equals(customer.getPassword())) {
            System.out.println("You're logged in " + customer.getFirstName());
            screen = new SelectAccountScreen(cRepo, customer);
        }
        else{
            System.out.println("We're sorry, we have no record of that account");
            screen = new LoginScreen(cRepo);
        }


        return screen;

    }

}
