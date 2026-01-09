package com.jpaSpringdamo.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "laptops")
public class Laptop {

    private  String name,model;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int L_id;
    @OneToOne(mappedBy = "laptop")
@JoinColumn(name = "user_id")
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getL_id() {
        return L_id;
    }

    public void setL_id(int l_id) {
        L_id = l_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
