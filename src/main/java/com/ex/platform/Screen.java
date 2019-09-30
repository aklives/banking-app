package com.ex.platform;

import com.ex.data.CustomerRepository;

import java.util.Scanner;

public interface Screen {
    Screen doScreen(Scanner scanner, CustomerRepository cRepo);
}
