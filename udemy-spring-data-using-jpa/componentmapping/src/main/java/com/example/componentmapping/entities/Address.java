package com.example.componentmapping.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {
    @Column(name = "streetaddress")
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
