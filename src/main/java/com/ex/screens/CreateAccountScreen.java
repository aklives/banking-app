package com.ex.screens;

import com.ex.data.CustomerRepository;
import com.ex.models.BankAccount;
import com.ex.models.Customer;
import com.ex.platform.Screen;
import com.ex.platform.StringMenuBuilder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateAccountScreen implements Screen {
    CustomerRepository cRepo;
    Customer customer;

    public CreateAccountScreen(CustomerRepository cRepo, Customer customer){
        this.cRepo = cRepo;
        this.customer = customer;
    }
    @Override
    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        System.out.println("Please choose the type of account: \n");
        String menuText = "";
        menuText = new StringMenuBuilder()
                .addOption("1", "Checking")
                .addOption("2", "Savings")
                .build();
        System.out.println(menuText);
        Screen screen = null;
        try {
            screen = doInput(scanner, cRepo, customer);
        } catch(InputMismatchException ex){
            System.out.println("Please enter a valid number");
            scanner.next();
            screen = new CreateAccountScreen(cRepo, customer);
        } catch(RuntimeException ex){
            System.out.println("Please enter a valid number.");
            scanner.next();
            screen = new CreateAccountScreen(cRepo, customer);
        } catch(Exception ex){
            System.out.println("Please input a valid number.");
            scanner.next();
            screen = new CreateAccountScreen(cRepo, customer);
        }
        return screen;
    }
    public Screen doInput(Scanner scanner, CustomerRepository cRepo, Customer customer){
        Screen newScreen = null;
        int i = scanner.nextInt();

        switch(i){
            case 1:

                System.out.println("You've created a Checking Account.");
                System.out.println("How much would you like to deposit?");
                int amount = scanner.nextInt();
                int balance = amount;
                String type = "Checking";
                BankAccount checkBankAccount = new BankAccount(0, customer.getId(), balance, type);
                System.out.println("Your checking account balance is " + checkBankAccount.getBalance());

                newScreen = new AccountScreen(cRepo, customer, checkBankAccount);
                break;
            case 2:
                System.out.println("You've created a Savings Account.");
                System.out.println("How much would you like to deposit?");
                amount = scanner.nextInt();
                balance = amount;
                type = "Savings";
                BankAccount saveBankAccount = new BankAccount(0, customer.getId(), balance, type);
                System.out.println("Your savings account balance is " + saveBankAccount.getBalance());

                newScreen = new AccountScreen(cRepo, customer, saveBankAccount);
                break;
            default:
                System.out.println("Please enter a valid number.");
                newScreen = new CreateAccountScreen(cRepo, customer);

        }
        return newScreen;
    }

}

