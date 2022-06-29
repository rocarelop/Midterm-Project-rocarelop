package com.ironhack.projectoRocioArellano.controller.impl;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountBalanceDTO;
import com.ironhack.projectoRocioArellano.controller.interfaces.AdminController;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import com.ironhack.projectoRocioArellano.repository.AccountRepository;
import com.ironhack.projectoRocioArellano.repository.AdminRepository;
import com.ironhack.projectoRocioArellano.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminControllerImpl implements AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AccountRepository accountRepository;

    //Traer todos las cuentas (comprobacion)
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    //Traer cuenta por id (comprobacion)
    @GetMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable int id){
        return adminService.findAccountByID(id);
    }

    //crear checking y si tiene menos de 24 años checkingstudent
    @PostMapping("/accounts/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid Checking checkingAccount){
        return adminService.createCheckingAccount(checkingAccount);
    }


    @PatchMapping("/accounts/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable int id, @RequestBody @Valid AccountBalanceDTO accountBalanceDTO){
    adminService.updateBalance(id, accountBalanceDTO.getBalance());

    }
}
