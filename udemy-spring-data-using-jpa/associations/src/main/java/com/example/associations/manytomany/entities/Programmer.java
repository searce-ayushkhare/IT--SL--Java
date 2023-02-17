package com.example.associations.manytomany.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "programmer")
@Getter
@Setter
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "programmers_projects",
            joinColumns = @JoinColumn(
                    name = "programmer_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Project> projects;

    @Override
    public String toString() {
        return "ID :: " + getId() + ", Name :: " + getName() + ", Salary :: " + getSalary();
    }
}
