package com.ironhack.projectoRocioArellano.controller.impl;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountHolderDTO;
import com.ironhack.projectoRocioArellano.controller.interfaces.AccountHolderController;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.repository.AccountHolderRepository;
import com.ironhack.projectoRocioArellano.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;
   @GetMapping("/myAccounts")
   @ResponseStatus(HttpStatus.OK)
    public Set<Account> findMyAccountsById(@AuthenticationPrincipal AccountHolderDTO accountHolderDTO) {

       return accountHolderService.findMyAccountById(accountHolderDTO.getId());
    }
}
