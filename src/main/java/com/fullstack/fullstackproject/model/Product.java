package com.fullstack.fullstackproject.model;


import javax.persistence.*;
import java.security.InvalidParameterException;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    protected String name;
    protected float price;
    //protected Category category;
    protected String description;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    protected Shop shop;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new InvalidParameterException();
        }
        this.name = name;
    }

    public void setPrice(float price) {
        if (price < 0) {
            throw new InvalidParameterException();
        }
        this.price = price;
    }

    protected Product() {}

    public Product(String name, float price, String description) {
        if (name.equals("") || price < 0) {
            throw new InvalidParameterException();
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " [" + price + "] (" + description + ")";
    }
}
