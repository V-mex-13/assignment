package com.jpaSpringdamo.entity;


import jakarta.persistence.*;

@Entity
public class Mobile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;
 private String model,price,storge;


 // OWNING SIDE ✅
 @ManyToOne
 @JoinColumn(name = "man_id")
 private Man man;



    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStorge() {
        return storge;
    }

    public void setStorge(String storge) {
        this.storge = storge;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }
}
