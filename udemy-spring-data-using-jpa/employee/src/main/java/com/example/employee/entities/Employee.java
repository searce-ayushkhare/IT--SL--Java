package com.example.employee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Employee {
//    @TableGenerator(
//            name = "employee_gen",
//            table = "id_gen",
//            pkColumnName = "gen_name",
//            valueColumnName = "gen_val",
//            allocationSize = 100
//    )
    @GenericGenerator(name = "emp_id", strategy = "com.example.employee.CustomRandomIDGenerator")
    @GeneratedValue(generator = "emp_id")
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.TABLE,
//            generator = "employee_gen"
//    )
    private Long id;
    private String name;
}
