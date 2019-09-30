package com.ex.screens;

import com.ex.data.AccountRepository;
import com.ex.data.CustomerRepository;
import com.ex.data.dao.AccountRepositoryImpl;
import com.ex.models.BankAccount;
import com.ex.models.Customer;
import com.ex.platform.Screen;

import java.util.List;
import java.util.Scanner;

public class ListAccountScreen implements Screen {
    CustomerRepository cRepo;
    Customer customer;
    AccountRepository aRepo = new AccountRepositoryImpl();

    ListAccountScreen(CustomerRepository cRepo, Customer customer){
        this.cRepo = cRepo;
        this.customer = customer;
    }

    @Override
    public Screen doScreen(Scanner scanner, CustomerRepository cRepo) {
        Screen newScreen;
        List<BankAccount> bankAccounts = (List<BankAccount>) aRepo.findAll(customer.getId());
        System.out.println("Enter id number for this account:");
        for(BankAccount bankAccount : bankAccounts) {
            System.out.println(bankAccount);
        }
        int acct_num = scanner.nextInt();

        newScreen = new AccountScreen(cRepo, customer, aRepo.findById(acct_num));

        return newScreen;
    }
}
