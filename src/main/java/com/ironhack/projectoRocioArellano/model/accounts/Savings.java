package com.ironhack.projectoRocioArellano.model.accounts;

import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.AccountHolder;
import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Savings extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_currency"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_min")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_currency_min"))
    })
    private final Money minimumBalanceMin = new Money(new BigDecimal(100));
    private BigDecimal interestRate;
    private final BigDecimal interestRateMax= new BigDecimal(0.5);
    private StatusEnum statusEnum;



    public Savings() {
        this.minimumBalance = new Money(new BigDecimal(1000));
        this.interestRate = new BigDecimal(0.025);
    }

    public Savings(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate, Money minimumBalance, BigDecimal interestRate, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.statusEnum = statusEnum;
    }

    public Savings(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, Money minimumBalance, BigDecimal interestRate, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.statusEnum = statusEnum;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getMinimumBalanceMin() {
        return minimumBalanceMin;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRateMax() {
        return interestRateMax;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
