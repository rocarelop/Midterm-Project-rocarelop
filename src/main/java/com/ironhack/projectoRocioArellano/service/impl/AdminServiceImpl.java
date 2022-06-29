package com.ironhack.projectoRocioArellano.service.impl;

import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import com.ironhack.projectoRocioArellano.model.accounts.StudentChecking;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.repository.AccountRepository;
import com.ironhack.projectoRocioArellano.repository.AdminRepository;
import com.ironhack.projectoRocioArellano.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void updateBalance(int id, Money balance){
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        account.setBalance(balance);
        accountRepository.save(account);

    }

    public Account findAccountByID(int id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid account id"));

        return account;
    }

    public Account createCheckingAccount(Checking checkingAccount){
        Account account;
        LocalDate dateOfBirth = LocalDate.from(checkingAccount.getPrimaryOwner().getDateOfBirth().toInstant());
        Period edad = Period.between(dateOfBirth, LocalDate.now());

        if(edad.getYears() < 24){
            if(checkingAccount.getSecondaryOwner()==null){
                account= new StudentChecking(checkingAccount.getBalance(), checkingAccount.getSecretKey(), checkingAccount.getPrimaryOwner(), checkingAccount.getCreationDate(), checkingAccount.getStatusEnum());
            }else{
                account= new StudentChecking(checkingAccount.getBalance(), checkingAccount.getSecretKey(), checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getCreationDate(), checkingAccount.getStatusEnum());
            }

        }else{
            if(checkingAccount.getSecondaryOwner()==null){
                account= new Checking(checkingAccount.getBalance(), checkingAccount.getSecretKey(), checkingAccount.getPrimaryOwner(), checkingAccount.getCreationDate(), checkingAccount.getMinimumBalance(), checkingAccount.getMonthlyMaintenanceFee(),checkingAccount.getStatusEnum());
            }else{
                account= new Checking(checkingAccount.getBalance(), checkingAccount.getSecretKey(), checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(), checkingAccount.getCreationDate(), checkingAccount.getMinimumBalance(), checkingAccount.getMonthlyMaintenanceFee(),checkingAccount.getStatusEnum());
            }
        }
        return accountRepository.save(account);
    }


}
