package com.wipro.Login.Enitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name = "login")
public class User {

    @Id
    @Column(name = "empid")
    private int empid;
    @Column(name = "password")
    private String password;

    public User() {
    }
}
