package com.ironhack.projectoRocioArellano.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String direction;
    private int number;
    private String city;
    private String country;
}
