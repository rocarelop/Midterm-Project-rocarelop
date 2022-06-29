package com.ironhack.projectoRocioArellano.model.accounts;

import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@PrimaryKeyJoinColumn(name="id")
public class CreditCard extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_max_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_max_currency"))
    })
    private final Money creditLimitMax= new Money(new BigDecimal(100000));
    private BigDecimal interestRate;
    private final BigDecimal interestRateMin = new BigDecimal(0.1);



    public CreditCard() {
        this.interestRate = new BigDecimal(0.2);
        this.creditLimit = new Money(new BigDecimal(100));
    }



    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Money getCreditLimitMax() {
        return creditLimitMax;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRateMin() {
        return interestRateMin;
    }
}
