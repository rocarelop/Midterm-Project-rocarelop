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

    Account createCheckingAccount(Checking checkingAccount);

    Account createCreditCardAccount(CreditCard creditCardAccount);

    Account createSavingsAccount(Savings savingsAccount);

    void updateBalance(int id, AccountBalanceDTO accountBalanceDTO);

    Money getBalance(int id);

    void deleteAccount(int id);
}
