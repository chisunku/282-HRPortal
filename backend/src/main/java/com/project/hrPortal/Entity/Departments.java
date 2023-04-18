package com.project.hrPortal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Departments")
public class Departments {
    @Id@Getter@Setter
    String deptNo;
    @Getter@Setter
    String deptName;
}
