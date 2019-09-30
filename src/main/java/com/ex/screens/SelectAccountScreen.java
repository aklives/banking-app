package com.ex.screens;

import com.ex.data.CustomerRepository;
import com.ex.models.Customer;
import com.ex.platform.Screen;
import com.ex.platform.StringMenuBuilder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectAccountScreen implements Screen {
    CustomerRepository cRepo;
    Customer customer;

    public SelectAccountScreen(CustomerRepository cRepo, Customer customer){
        this.cRepo = cRepo;
        this.customer = customer;
    }

    @Override
    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        String menuText = "";
        menuText = new StringMenuBuilder()
                .addOption("1", "Create an Account")
                .addOption("2", "Select an Account")
                .addOption("3", "Exit")
                .build();
        System.out.println(menuText);
        Screen screen = null;
        try {
            screen = doInput(scanner, customer, cRepo);
        } catch(InputMismatchException ex){
            System.out.println("Please enter a valid number");
            scanner.next();
            screen = new SelectAccountScreen(cRepo, customer);
        } catch(RuntimeException ex){
            System.out.println("Please enter a valid number.");
            scanner.next();
            screen = new SelectAccountScreen(cRepo, customer);
        } catch(Exception ex){
            System.out.println("Please input a valid number.");
            scanner.next();
            screen = new SelectAccountScreen(cRepo, customer);
        }
        return screen;
    }

    public Screen doInput(Scanner scanner, Customer customer, CustomerRepository cRepo){
        Screen newScreen = null;
        int i = scanner.nextInt();



        switch(i){
            case 1:
                newScreen = new CreateAccountScreen(cRepo, customer);
                break;
            case 2:
                newScreen = new ListAccountScreen(cRepo, customer);
                break;
            case 3:
                System.out.println("Thank you for banking with us!");
                break;
            default:
                System.out.println("Please enter a valid number.");
                newScreen = new SelectAccountScreen(cRepo, customer);

        }
        return newScreen;
    }


}
