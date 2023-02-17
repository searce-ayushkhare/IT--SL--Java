package com.example.componentmapping.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    private int id;
    private String name;

    @Embedded
    private Address address;
}
