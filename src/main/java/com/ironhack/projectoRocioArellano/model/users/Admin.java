package com.ironhack.projectoRocioArellano.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User{


}
