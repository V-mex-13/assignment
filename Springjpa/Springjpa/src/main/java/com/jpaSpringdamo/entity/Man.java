package com.jpaSpringdamo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Man {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer man_id;
    private String name;

    public int getMan_id() {
        return man_id;
    }

    public void setMan_id(int man_id) {
        this.man_id = man_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mobile> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<Mobile> mobileList) {
        this.mobileList = mobileList;
    }

    @OneToMany(mappedBy = "man",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Mobile> mobileList = new ArrayList<>();
}
