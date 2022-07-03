package com.ironhack.projectoRocioArellano.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

}
