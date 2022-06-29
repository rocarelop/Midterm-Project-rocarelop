package com.ironhack.projectoRocioArellano.controller.interfaces;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountBalanceDTO;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;

import java.util.List;

public interface AdminController {
    List<Account> findAll();

    Account findAccountById(int id);

    Account store(Checking checkingAccount);

    void updateBalance(int id, AccountBalanceDTO accountBalanceDTO);
}
