package com.ironhack.projectoRocioArellano.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransferDestino {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountReceiverId;

    private String nameReceiver;

    public TransferDestino() {
    }

    public TransferDestino(String nameReceiver) {
        this.nameReceiver = nameReceiver;
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
