package com.project.hrPortal.Entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

public class TitlesId implements Serializable {
    @Getter@Setter
    int empNo;
    @Getter@Setter
    String title;
    @Getter@Setter
    Date fromDate;
}
