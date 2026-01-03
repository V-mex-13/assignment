package com.jpaSpringdamo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {



    private String name,address,email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
