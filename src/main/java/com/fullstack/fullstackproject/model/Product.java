package com.fullstack.fullstackproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    @ManyToMany
    protected Set<Category> categories;
    protected String description;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference
    protected Shop shop;

    @JsonSerialize
    @JsonProperty("shop_id")
    protected Long getShopId() {
        return getShop().getId();
    }

    @JsonSerialize
    @JsonProperty("shop_name")
    protected String getShopName() {
        return getShop().getName();
    }

    protected Shop getShop() {
        return shop;
    }

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


    public void setCategories(Set<Category> newCategories) {
        categories = newCategories;
    }


    public Set<Category> getCategories() {
        return categories;
    }

    protected Product() {
        categories = new HashSet<>();
    }


    public Product(String name, float price, String description) {
        this(name, price, description, null);
    }

    public Product(String name, float price, String description, Shop shop) {
        if (name == null || name.equals("") || price < 0) {
            throw new InvalidParameterException();
        }
        categories = new HashSet<>();
        this.name = name;
        this.price = price;
        this.description = description;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return name + " [" + price + "] (" + description + ")";
    }
}
