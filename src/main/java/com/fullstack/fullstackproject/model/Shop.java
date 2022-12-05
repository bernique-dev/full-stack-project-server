package com.fullstack.fullstackproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    protected String name;

    @NotNull
    protected Boolean isOnLeave;


    protected String openingTimes;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    protected List<Product> productList;

    @NotNull
    protected LocalDate creationDate;

    public Shop() {}

    public Shop(String name, Boolean isOnLeave, String openingTimes, LocalDate creationDate) {
        if (name.equals("")
                || isOnLeave == null
                || openingTimes.equals("")
                || creationDate == null) {
            throw new InvalidParameterException();
        }

        this.name = name;
        this.isOnLeave = isOnLeave;
        this.openingTimes = openingTimes;
        this.productList = new ArrayList<>();
        this.creationDate = creationDate;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsOnLeave() {
        return this.isOnLeave;
    }

    public String getOpeningTimes() { return this.openingTimes; }

    public List<Product> getProductList() {
        return this.productList;
    }

    public LocalDate getCreationDate() {return this.creationDate;}

    public void setName(String name) {
        if (name.equals("")) {
            throw new InvalidParameterException();
        }
        this.name = name;
    }

    public void setIsOnLeave(Boolean isOnLeave) {
        if (isOnLeave == null) {
            throw new InvalidParameterException();
        }
        this.isOnLeave = isOnLeave;
    }

    public void setOpeningTimes(String horaries) {
        this.openingTimes = horaries;
    }

    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate;}

    public void addProduct(Product product) {
        if (product == null) {
            throw new InvalidParameterException();
        }
        productList.add(product);
    }

    public void deleteProduct(Product product) {
        productList.remove(product);
    }

}
