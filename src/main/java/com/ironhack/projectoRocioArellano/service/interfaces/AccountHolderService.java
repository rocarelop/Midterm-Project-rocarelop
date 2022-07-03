package com.ironhack.projectoRocioArellano.service.interfaces;

import com.ironhack.projectoRocioArellano.controller.DTO.SendMoneyDTO;
import com.ironhack.projectoRocioArellano.model.accounts.Account;

import java.util.Set;

public interface AccountHolderService {

    Set<Account> findAllMyAccounts(int id);

    void sendMoney(int id, int id1, SendMoneyDTO sendMoneyDTO);
}
