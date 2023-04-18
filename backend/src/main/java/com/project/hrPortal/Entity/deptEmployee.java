package com.project.hrPortal.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "dept_emp")
@IdClass(deptEmpId.class)
public class deptEmployee {
    @Id
    @Getter@Setter
    int empNo;
    @Id
    @Getter@Setter
    String deptNo;
    @Getter@Setter
    Date fromDate;
    @Getter@Setter
    Date toDate;
//    @OneToMany
//    @JoinColumn(name = "empNo", referencedColumnName = "empNo")
//    Employees employees;
//    @OneToMany
}
