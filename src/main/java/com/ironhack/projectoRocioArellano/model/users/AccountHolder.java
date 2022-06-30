package com.ironhack.projectoRocioArellano.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.projectoRocioArellano.model.Address;
import com.ironhack.projectoRocioArellano.model.accounts.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class AccountHolder extends User {

    private Date dateOfBirth;

    @Embedded
    private Address primaryAddress;

    private Integer mailingAddress; //opcional

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private Set<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private Set<Account> secondaryAccounts;

    public AccountHolder() {
    }

    public AccountHolder(String name, String username, String password, Set<Role> roles, Date dateOfBirth, Address primaryAddress, Integer mailingAddress, Set<Account> primaryAccounts, Set<Account> secondaryAccounts) {
        super(name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.primaryAccounts = primaryAccounts;
        this.secondaryAccounts = secondaryAccounts;
    }

    public AccountHolder(String name, String username, String password, Set<Role> roles, Date dateOfBirth, Address primaryAddress, Integer mailingAddress, Set<Account> primaryAccounts) {
        super(name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.primaryAccounts = primaryAccounts;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Integer getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Integer mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Set<Account> getPrimaryAccounts() {
        return primaryAccounts;
    }

    public void setPrimaryAccounts(Set<Account> primaryAccounts) {
        this.primaryAccounts = primaryAccounts;
    }

    public Set<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }

    public void setSecondaryAccounts(Set<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
    }
}




