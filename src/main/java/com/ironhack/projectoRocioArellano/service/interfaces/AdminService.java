package com.ironhack.projectoRocioArellano.service.interfaces;

import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import com.ironhack.projectoRocioArellano.model.accounts.CreditCard;
import com.ironhack.projectoRocioArellano.model.accounts.Savings;
import com.ironhack.projectoRocioArellano.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {
    public void updateBalance(int id, Money balance);

    Account findAccountByID(int id);

    Account createCheckingAccount(Checking checkingAccount);

    Account createCreditCardAccount(CreditCard creditCardAccount);

    Account createSavingsAccount(Savings savingsAccount);

    void deleteAccount(int id);
}
