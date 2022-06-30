package com.ironhack.projectoRocioArellano.service.interfaces;

import com.ironhack.projectoRocioArellano.model.accounts.Account;

import java.util.Set;

public interface AccountHolderService {

    Set<Account> findMyAccountById(int id);

}
