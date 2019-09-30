package com.ex.screens;

import com.ex.data.AccountRepository;
import com.ex.data.CustomerRepository;
import com.ex.data.dao.AccountRepositoryImpl;
import com.ex.models.BankAccount;
import com.ex.models.Customer;
import com.ex.platform.Screen;

import java.util.Scanner;

public class AccountScreen implements Screen {

    Scanner sc = new Scanner(System.in);
    AccountRepository aRepo = new AccountRepositoryImpl();
    CustomerRepository cRepo;
    Customer customer;
    BankAccount bankAccount;

    public AccountScreen(CustomerRepository cRepo, Customer customer, BankAccount bankAccount) {
        this.cRepo = cRepo;
        this.customer = customer;
        this.bankAccount = bankAccount;

        int newId = aRepo.save(bankAccount);
        if (newId > 0) {
            bankAccount.setId(newId);
//            System.out.println(bankAccount);
        }
        System.out.println();


        boolean exit = false;


        while (exit == false) {



            System.out.println("");
            System.out.println("Account Screen:");
            System.out.println("Enter 1 to Check Your Balance.");
            System.out.println("Enter 2 to Make A Deposit.");
            System.out.println("Enter 3 to Make A Withdrawal.");
            System.out.println("Enter 4 to Return To Accounts ");

            int input = sc.nextInt();


            switch (input) {
                case 1:
                    System.out.println("Your balance is " + bankAccount.getBalance());
                    break;
                case 2:
                    System.out.println("Enter an amount to deposit.");
                    int amount = sc.nextInt();
                    bankAccount.deposit(amount);
                    System.out.println("Your new balance is " + bankAccount.getBalance());
                    aRepo.update(bankAccount);
                    break;
                case 3:
                    System.out.println("Enter an amount to withdraw.");
                    amount = sc.nextInt();
                    bankAccount.withdraw(amount);
                    System.out.println("Your new balance is " + bankAccount.getBalance());
                    aRepo.update(bankAccount);
                    break;
                case 4:
                    doScreen(sc, cRepo);
                    exit = true;
                    continue;

            }
        }

    }

    @Override
    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        Screen newScreen = new SelectAccountScreen(cRepo, customer);
        return newScreen;
    }
}


