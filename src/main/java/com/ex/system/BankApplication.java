package com.ex.system;

import com.ex.data.CustomerRepository;
import com.ex.platform.Application;
import com.ex.platform.Screen;
import com.ex.screens.WelcomeScreen;

import java.util.Scanner;

public class BankApplication extends Application {
    private Screen currentScreen;
    private Scanner scanner;

    public BankApplication(){
        currentScreen = new WelcomeScreen();
        scanner = new Scanner(System.in);
    }
    public void run(CustomerRepository cRepo) {
        while(currentScreen != null){
            currentScreen = currentScreen.doScreen(scanner, cRepo);
        }
    }
}