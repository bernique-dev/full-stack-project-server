package com.fullstack.fullstackproject.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.security.InvalidParameterException;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    protected String name;

    @NotNull
    @PositiveOrZero
    protected Float price;
    //protected Category category;
    protected String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new InvalidParameterException();
        }
        this.name = name;
    }

    public void setPrice(Float price) {
        if (price < 0) {
            throw new InvalidParameterException();
        }
        this.price = price;
    }

    protected Product() {}

    public Product(String name, float price, String description) {
        if (name == null || name.equals("") || price < 0) {
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
