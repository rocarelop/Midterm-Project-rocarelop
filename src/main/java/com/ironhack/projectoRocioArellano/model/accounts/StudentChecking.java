package com.ironhack.projectoRocioArellano.model.accounts;

import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.AccountHolder;
import com.ironhack.projectoRocioArellano.model.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
@Entity
@PrimaryKeyJoinColumn(name="id")
public class StudentChecking extends Account {
    private StatusEnum statusEnum;

    public StudentChecking() {
    }


    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate);
        this.statusEnum = statusEnum;
    }

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, Date creationDate, StatusEnum statusEnum) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.statusEnum = statusEnum;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
