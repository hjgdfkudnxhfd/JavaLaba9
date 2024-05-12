package com.example.model;

public class Product {
    private int id;
    private String name;
    private boolean isMarked;

    public Product(int id, String currentProductName){
        this.id=id;
        name=currentProductName;
        this.isMarked=false;
    }

    public int getId() {
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