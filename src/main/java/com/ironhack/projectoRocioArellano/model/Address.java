package com.ironhack.projectoRocioArellano.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String direction;
    private int number;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String direction, int number, String city, String country) {
        this.direction = direction;
        this.number = number;
        this.city = city;
        this.country = country;
    }

}
