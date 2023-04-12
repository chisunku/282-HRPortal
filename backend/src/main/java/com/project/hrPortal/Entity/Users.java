package com.project.hrPortal.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class Users {
    @Id
    @Getter@Setter
    private int empId;
    @Getter@Setter
    private String password;
}
