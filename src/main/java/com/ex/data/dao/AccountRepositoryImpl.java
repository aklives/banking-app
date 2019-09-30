package com.ex.data.dao;

import com.ex.Main;
import com.ex.data.AccountRepository;
import com.ex.models.BankAccount;
import com.ex.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public BankAccount findByCustId(Integer userId) {
        // format for Simple Statement
//        String sql = "SELECT id, first_name, last_name, email, hash from USERS where email = '" + email + "'";

        // format for PreparedStatement
        String sql = "SELECT id, balance, type from ACCOUNTS where userId = ?";

//        System.out.println("Executing statement \n " + sql);
        try {
            Connection connection = Main.manager.getConnection();
//            Statement statement = c.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet results = statement.executeQuery();

            BankAccount bankAccount = null;
            while (results.next()) {
                int id = results.getInt("id");
                int balance = results.getInt("balance");
                String type = results.getString("type");

                bankAccount = new BankAccount(id, userId, balance, type);
            }
            return bankAccount;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BankAccount findById(int bankId) {
        // format for Simple Statement
//        String sql = "SELECT id, first_name, last_name, email, hash from USERS where email = '" + email + "'";

        // format for PreparedStatement
        String sql = "SELECT userid, balance, type from ACCOUNTS where id = ?";

//        System.out.println("Executing statement \n " + sql);
        try {
            Connection connection = Main.manager.getConnection();
//            Statement statement = c.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bankId);

            ResultSet results = statement.executeQuery();

            BankAccount bankAccount = null;
            while (results.next()) {
                int userId = results.getInt("userid");
                int balance = results.getInt("balance");
                String type = results.getString("type");

                bankAccount = new BankAccount(bankId, userId, balance, type);
            }
            return bankAccount;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Collection<BankAccount> findAll(int uId) {

        //format for callable statement
        String sql = "{ call get_all_customer_accounts(?) }";

//        System.out.println("Executing statement \n " + sql);
        try {
            Connection c = Main.manager.getConnection();
            CallableStatement statement = c.prepareCall(sql);
            statement.setInt(1, uId);

            ResultSet results = statement.executeQuery();

            List<BankAccount> bankAccounts = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                int userId = results.getInt("user_id");
                int balance = results.getInt("balance");
                String type = results.getString("type");


                bankAccounts.add(new BankAccount(id, userId, balance, type));
            }
            return bankAccounts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void delete(BankAccount obj) {

    }

    @Override
    public Integer save(BankAccount obj) {
        // check the obj for an id
        // 0 id means this object has never been persisted 'insert'
        // otherwise 'update'
        int out_id = 0;
        Connection connection = null;
        if (obj.getId() == 0) {
            // never saved 'insert'
            String sql = "{ ? = call add_new_bankAccount(?, ?, ?) }";

//            System.out.println("Executing statement \n " + sql);
            try {
                connection = Main.manager.getConnection();
                CallableStatement statement = connection.prepareCall(sql);

                // register the out parameter to get the value returned from
                // the callable statement.
                // Hopefully the new id
                statement.registerOutParameter(1, Types.INTEGER);

                statement.setInt(2, obj.getUserId());
                statement.setInt(3, obj.getBalance());
                statement.setString(4, obj.getType());


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
    public void update(BankAccount bankAccount){
        Connection connection = null;
        String sql = "{ call update_bankaccount(?, ?) }";
//        System.out.println("Executing statement \n " + sql);

        try {
            connection = Main.manager.getConnection();
            CallableStatement statement = connection.prepareCall(sql);

            // register the out parameter to get the value returned from
            // the callable statement.
            // Hopefully the new id
//            statement.registerOutParameter(1, Types.INTEGER);

            statement.setInt(1, bankAccount.getId());
            statement.setInt(2, bankAccount.getBalance());
//            statement.setString(4, obj.getType());


            // turn of auto-commit
            // we want to control the transaction
            connection.setAutoCommit(false);

            statement.execute();

            // The result is stored on the statement
//            out_id = (Integer) statement.getObject(1);


            // everything went well commit and reset the database auto-commit flag
            connection.commit();
            connection.setAutoCommit(true);


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

}

