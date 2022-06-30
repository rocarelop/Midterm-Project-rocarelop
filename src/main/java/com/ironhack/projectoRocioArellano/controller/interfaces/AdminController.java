package com.ironhack.projectoRocioArellano.controller.interfaces;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountBalanceDTO;
import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import com.ironhack.projectoRocioArellano.model.accounts.CreditCard;
import com.ironhack.projectoRocioArellano.model.accounts.Savings;

import java.util.List;

public interface AdminController {
    List<Account> findAll();

    Account findAccountById(int id);

    Account store(Checking checkingAccount);

    Account store(CreditCard creditCardAccount);

    Account store(Savings savingsAccount);

    void updateBalance(int id, AccountBalanceDTO accountBalanceDTO);

    Money getBalance(int id);
}
