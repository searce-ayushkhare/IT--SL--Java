package com.example.product.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private int id;
    private String name;

    @Column(name = "description")
    private String desc;
    private Double price;
}
