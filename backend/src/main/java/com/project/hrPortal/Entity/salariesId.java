package com.project.hrPortal.Entity;

//import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

public class salariesId implements Serializable {
    @Getter@Setter
    int empNo;
    @Getter@Setter
    Date fromDate;
}
