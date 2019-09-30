package com.ex.screens;

import com.ex.data.CustomerRepository;
import com.ex.platform.Screen;
import com.ex.screens.LoginScreen;
import com.ex.platform.StringMenuBuilder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen implements Screen{

    public Screen doScreen(Scanner scanner, CustomerRepository cRepo){

        System.out.println("Welcome to Revature Bank \n");
        String menuText = "";
        menuText = new StringMenuBuilder()
                .addOption("1", "Register")
                .addOption("2", "Login")
                .addOption("3", "Exit")
                .build();
        System.out.println(menuText);
        Screen screen = null;

        try {
            screen = doInput(scanner, cRepo);
        } catch(InputMismatchException ex){
            System.out.println("Please enter a valid number");
            scanner.next();
            screen = new WelcomeScreen();
        } catch(RuntimeException ex){
            System.out.println("Please enter a valid number.");
            scanner.next();
            screen = new WelcomeScreen();
        } catch(Exception ex){
            System.out.println("Please input a valid number.");
            scanner.next();
            screen = new WelcomeScreen();
        }
        return screen;
    }

    public Screen doInput(Scanner scanner, CustomerRepository cRepo){
        Screen newScreen = null;
        int i = scanner.nextInt();



        switch(i){
            case 1:
                newScreen = new RegisterScreen(cRepo);
                break;
            case 2:
                newScreen = new LoginScreen(cRepo);
                break;
            case 3:
                System.out.println("Thank you for banking with us!");
                break;
            default:
                System.out.println("Please enter a valid number.");
                newScreen = new WelcomeScreen();

        }
        return newScreen;
    }
}

