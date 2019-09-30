package com.ex;

import com.ex.data.AccountRepository;
import com.ex.data.CustomerRepository;
import com.ex.data.dao.AccountRepositoryImpl;
import com.ex.data.dao.CustomerRepositoryImpl;
import com.ex.models.Customer;
import com.ex.platform.Application;
import com.ex.system.BankApplication;
import com.ex.utils.ConnectionManager;
import com.ex.utils.PostgresConnectionManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static ConnectionManager manager;

    public static void main(String[] args) {
        Application app = new BankApplication();

        app.run(initContext());
    }

    public static CustomerRepository initContext() {
        Properties properties = new Properties();
//        properties.setProperty(PostgresConnectionManager.DB_USERNAME_KEY, "august_duet");
//        properties.setProperty(PostgresConnectionManager.DB_PASSWORD_KEY, "thisISmahPassword123");
//        properties.setProperty(PostgresConnectionManager.DB_URL_KEY, DB_URL);


        try {
            String fileName = ClassLoader
                    .getSystemClassLoader().getResource("db_conn.properties")
                    .getFile();

            properties.load(new FileReader(fileName));

            manager = new PostgresConnectionManager(properties);

            //why is ref variable CustRepo and not CustRepoImp? polymorphism?
            CustomerRepository cRepo = new CustomerRepositoryImpl();

            return cRepo;
//            Customer customer = cRepo.findByEmail("hayden.fields@gmail.com");
//
//            if(customer != null) {
//                System.out.println(customer);
//            } else {
//                System.out.println("No user found");
//            }
//            System.out.println();

//            customer = new Customer(0, "Elyse", "Lam", "elyse.lam@gmail.com", "1234567890123456789012345678901234567890");
//            int newId = cRepo.save(customer);
//            if(newId > 0) {
//                customer.setId(newId);
//                System.out.println(customer);
//            }
//            System.out.println();

//            List<Customer> users = (List<Customer>) cRepo.findAll();
//            for(Customer user : users) {
//                System.out.println(user);
//            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
