package com.ironhack.projectoRocioArellano.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String hashKey;

    public ThirdParty() {
    }

    public ThirdParty(String hashKey) {
        this.hashKey = hashKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
