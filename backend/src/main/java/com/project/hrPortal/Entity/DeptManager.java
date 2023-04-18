package com.project.hrPortal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "dept_manager")
@IdClass(deptMgrId.class)
public class DeptManager {
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

}
