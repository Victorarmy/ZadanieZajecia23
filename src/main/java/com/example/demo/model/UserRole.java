package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String role;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
