package com.ex.data;

import com.ex.models.BankAccount;

import java.util.Collection;

public interface AccountRepository extends Repository<Integer, BankAccount> {

    BankAccount findByCustId(Integer integer);

    BankAccount findById(int bankId);

    void update(BankAccount bankAccount);

    public Collection<BankAccount> findAll(int id);
}
