package com.example.hibernateinheritance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Card")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
//@DiscriminatorValue("cc")
public class CreditCard extends Payment {
    private String cardnumber;
}
