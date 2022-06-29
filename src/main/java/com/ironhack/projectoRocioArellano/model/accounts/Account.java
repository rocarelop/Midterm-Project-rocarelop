package com.ironhack.projectoRocioArellano.model.accounts;

import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money balance;

    private String secretKey;
    @ManyToOne
    @JoinColumn(name="id_primary_owner")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name="id_secondary_owner")
    private AccountHolder secondaryOwner; // opcional
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    private final Money penaltyFee = new Money(new BigDecimal(40));
    private Date creationDate;

    public Account() {


    }

    public Account(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
    }

    public Account(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = null;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
