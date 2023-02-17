package com.example.hibernateinheritance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bankcheque")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
//@DiscriminatorValue("ch")
public class Cheque extends Payment {
    private String chequenumber;
}
