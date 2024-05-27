package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "isMarked", nullable = false)

    private boolean isMarked;

    public Product(String currentProductName){
        this.id=id;
        name=currentProductName;
        this.isMarked=false;
    }

    public Product(){}
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getIsMarked() {
        return isMarked;
    }

    public void setName(String currentProductNewName) {
        name = currentProductNewName;
    }
    public void setIsMarked() {
        isMarked = !isMarked;
    }

}