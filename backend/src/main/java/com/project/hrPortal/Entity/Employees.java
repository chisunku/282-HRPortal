package com.project.hrPortal.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employees {
    @Id
    @Getter@Setter
    private int empNo;
    @Setter@Getter
    Date birthDate;
    @Setter@Getter
    String firstName;
    @Setter@Getter
    String lastName;
    @Getter@Setter
    String gender;
    @Getter@Setter
    Date hireDate;
//    @OneToMany(cascade = CascadeType.ALL)
//    List<Titles> titles;
}
