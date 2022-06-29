package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountBalanceDTO;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer > {
   /* List<Account> findAll();

    Account findAccountById(int id);*/



}
