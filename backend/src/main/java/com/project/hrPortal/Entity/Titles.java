package com.project.hrPortal.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@IdClass(TitlesId.class)
@Table(name = "Titles")
public class Titles {
    @Id
    @Getter@Setter
    int empNo;
    @Id
    @Getter@Setter
    String title;
    @Id
    @Getter@Setter
    Date fromDate;
    @Getter@Setter
    Date toDate;
    @ManyToOne
    @JoinColumn(name = "empNo", referencedColumnName = "empNo",  insertable = false, updatable = false)
    Employees employee;
}
