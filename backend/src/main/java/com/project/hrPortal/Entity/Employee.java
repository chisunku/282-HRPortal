package com.project.hrPortal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "Employee")
public class Employee {
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
}
