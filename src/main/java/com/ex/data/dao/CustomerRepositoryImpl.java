package com.ex.data.dao;

import com.ex.Main;
import com.ex.data.CustomerRepository;
import com.ex.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findByEmail(String email) {
        // format for Simple Statement
//        String sql = "SELECT id, first_name, last_name, email, hash from USERS where email = '" + email + "'";

        // format for PreparedStatement
        String sql = "SELECT id, first_name, last_name, email, password from USERS where email = ?";

//        System.out.println("Executing statement \n " + sql);
        try {
            Connection connection = Main.manager.getConnection();
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();

            Customer customer = null;
            while (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String _email = results.getString("email");
                String password = results.getString("password");

                customer = new Customer(id, firstName, lastName, _email, password);
            }
            return customer;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findByCustId(Integer integer) {
        return null;
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public Collection<Customer> findAll(int id) {

//        //format for callable statement
//        String sql = "{ call get_all_users() }";
//
//        System.out.println("Executing statement \n " + sql);
//        try {
//            Connection c = Main.manager.getConnection();
//            CallableStatement statement = c.prepareCall(sql);
//
//            ResultSet results = statement.executeQuery();
//
//            List<Customer> users = new ArrayList<>();
//            while (results.next()) {
//                int id = results.getInt("id");
//                String firstName = results.getString("first_name");
//                String lastName = results.getString("last_name");
//                String _email = results.getString("email");
//                String hash = results.getString("hash");
//
//                users.add(new Customer(id, firstName, lastName, _email, hash));
//            }
//            return users;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public Integer save(Customer obj) {
        // check the obj for an id
        // 0 id means this object has never been persisted 'insert'
        // otherwise 'update'
        int out_id = 0;
        Connection connection = null;
        if (obj.getId() == 0) {
            // never saved 'insert'
            String sql = "{ ? = call add_new_user(?, ?, ?, ?) }";

//            System.out.println("Executing statement \n " + sql);
            try {
                connection = Main.manager.getConnection();
                CallableStatement statement = connection.prepareCall(sql);

                // register the out parameter to get the value returned from
                // the callable statement.
                // Hopefully the new id
                statement.registerOutParameter(1, Types.INTEGER);

                statement.setString(2, obj.getFirstName());
                statement.setString(3, obj.getLastName());
                statement.setString(4, obj.getEmail());
                statement.setString(5, obj.getPassword());

                // turn of auto-commit
                // we want to control the transaction
                connection.setAutoCommit(false);

                statement.execute();

                // The result is stored on the statement
                out_id = (Integer) statement.getObject(1);


                // everything went well commit and reset the database auto-commit flag
                connection.commit();
                connection.setAutoCommit(true);

                return out_id;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if (connection != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        return 0;
    }

    @Override
    public void delete(Customer obj) {

    }
}
