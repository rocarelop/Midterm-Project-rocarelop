package com.ironhack.projectoRocioArellano.model;

import com.ironhack.projectoRocioArellano.model.accounts.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Date dateOfBirth;

    @Embedded
    private Address primaryAddress;

    private Integer mailingAddress; //opcional

    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> secondaryAccounts;


    public AccountHolder() {
    }

    public AccountHolder(String name, Date dateOfBirth, Address primaryAddress, int mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, Date dateOfBirth, Address primaryAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(int mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
