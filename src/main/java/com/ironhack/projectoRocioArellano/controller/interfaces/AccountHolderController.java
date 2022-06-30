package com.ironhack.projectoRocioArellano.controller.interfaces;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountHolderDTO;
import com.ironhack.projectoRocioArellano.model.accounts.Account;

import java.util.Set;

public interface AccountHolderController {
    Set<Account> findMyAccountsById(AccountHolderDTO accountHolderDTO);
}
