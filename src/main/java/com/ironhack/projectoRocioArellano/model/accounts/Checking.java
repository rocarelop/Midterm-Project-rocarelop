package com.ironhack.projectoRocioArellano.model.accounts;

import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.AccountHolder;
import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Checking extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_currency"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private Money monthlyMaintenanceFee;
    private StatusEnum statusEnum;

    public Checking() {
        this.minimumBalance = new Money(new BigDecimal(250));
        this.monthlyMaintenanceFee = new Money(new BigDecimal(12));
    }


    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, Money minimumBalance, Money monthlyMaintenanceFee, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.statusEnum = statusEnum;
    }

    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate, Money minimumBalance, Money monthlyMaintenanceFee, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.statusEnum = statusEnum;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
