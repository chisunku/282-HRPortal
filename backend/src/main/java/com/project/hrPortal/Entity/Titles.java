package com.project.hrPortal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
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
}
