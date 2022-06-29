package com.ironhack.projectoRocioArellano.model.users;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User{


}
