package com.project.hrPortal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "salaries")
@IdClass(salariesId.class)
public class salaries {
    @Id
    @Getter@Setter
    int empNo;
    @Id
    @Getter@Setter
    Date fromDate;
    @Getter@Setter
    int salary;
    @Getter@Setter
    Date toDate;
}
