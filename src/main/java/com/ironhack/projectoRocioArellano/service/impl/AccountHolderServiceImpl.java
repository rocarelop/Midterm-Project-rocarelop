package com.ironhack.projectoRocioArellano.service.impl;

import com.ironhack.projectoRocioArellano.controller.DTO.SendMoneyDTO;
import com.ironhack.projectoRocioArellano.model.Money;
//import com.ironhack.projectoRocioArellano.model.Transferencia;
import com.ironhack.projectoRocioArellano.model.Transferencia;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.repository.AccountHolderRepository;
import com.ironhack.projectoRocioArellano.repository.AccountRepository;
import com.ironhack.projectoRocioArellano.repository.TransferenciaRepository;
import com.ironhack.projectoRocioArellano.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;


    public Set<Account> findAllMyAccounts(int id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Account> accountList = accountRepository.findByPrimaryOwner(accountHolder);
        Set<Account> accounts = new HashSet<>(accountList);
        return accounts;
    }

    public void sendMoney(int id, int id1, SendMoneyDTO sendMoneyDTO) {
        Account accountSender = accountRepository.findById(sendMoneyDTO.getAccountSenderId()).get();
        String nameSender = accountHolderRepository.findById(id).get().getName();
        Account accountReceiver = accountRepository.findById(sendMoneyDTO.getAccountReceiverId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid account ID"));

        Transferencia transferencia;
        if (accountReceiver.getSecondaryOwner() != null) {
            if (accountReceiver.getPrimaryOwner().getName().equals(sendMoneyDTO.getNameReceiver()) || accountReceiver.getSecondaryOwner().getName().equals(sendMoneyDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(sendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(sendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                transferencia = new Transferencia(nameSender, sendMoneyDTO.getAmountMoney());

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer receiver's name is invalid");
            }
        } else {
            if (accountReceiver.getPrimaryOwner().getName().equals(sendMoneyDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(sendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(sendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                transferencia = new Transferencia(nameSender, sendMoneyDTO.getAmountMoney());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer receiver's name is invalid");
            }
            accountRepository.saveAll(List.of(accountReceiver));



        }
    }

}
