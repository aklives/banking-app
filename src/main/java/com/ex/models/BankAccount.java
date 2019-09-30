package com.ex.models;

public class BankAccount {
    private int id;
    private int userId;
    private int balance;
    private String type;

    public BankAccount(int id, int userId, int balance, String type){
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.type = type;
    }



    public BankAccount() {
        setBalance(0);
    }
    public void deposit(int amount){
        if(amount > 0){
            setBalance(getBalance() + amount);
        }
    }
    public void withdraw(int amount){
        if(amount > 0){
            setBalance(getBalance() - amount);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", balance='" + balance + '\'' +
                ", type='" + type + '\'' +
                "}";
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}