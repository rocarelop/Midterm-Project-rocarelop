package com.ironhack.projectoRocioArellano.model;

import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;

import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Transferencia extends Account {

    private String senderName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_money")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_money"))
    })
    private Money amountMoney;

    public Transferencia() {
    }

    public Transferencia(String senderName, Money amountMoney) {
        this.senderName = senderName;
        this.amountMoney = amountMoney;
    }

    public Transferencia(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String senderName, Money amountMoney) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate);
        this.senderName = senderName;
        this.amountMoney = amountMoney;
    }

    public Transferencia(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate, String senderName, Money amountMoney) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.senderName = senderName;
        this.amountMoney = amountMoney;
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
}
