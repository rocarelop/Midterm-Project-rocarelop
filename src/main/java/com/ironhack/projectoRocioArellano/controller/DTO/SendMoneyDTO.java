package com.ironhack.projectoRocioArellano.controller.DTO;

import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SendMoneyDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountSenderId;

    private String senderName;

    @Embedded
    private Money amountMoney;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountReceiverId;

    private String nameReceiver;

    public SendMoneyDTO() {
    }

    public SendMoneyDTO(String senderName, Money amountMoney, String nameReceiver) {
        this.senderName = senderName;
        this.amountMoney = amountMoney;
        this.nameReceiver = nameReceiver;
    }

    public int getAccountSenderId() {
        return accountSenderId;
    }

    public void setAccountSenderId(int accountSenderId) {
        this.accountSenderId = accountSenderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Money getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Money amountMoney) {
        this.amountMoney = amountMoney;
    }

    public int getAccountReceiverId() {
        return accountReceiverId;
    }

    public void setAccountReceiverId(int accountReceiverId) {
        this.accountReceiverId = accountReceiverId;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }
}


