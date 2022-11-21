package com.fullstack.fullstackproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "opening_times",
                    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "FK_opening_time_to_shop"),
                    joinColumns = @JoinColumn(name = "shop_id"))
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyColumn(name = "day")
    @Column(name = "opening_time")
    @NotNull
    //@JsonFormat(pattern = "hh:mm:ss")
    protected Map<DayOfWeek, LocalTime> openingTimes;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "closing_times",
                    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "FK_closing_time_to_shop"),
                    joinColumns = @JoinColumn(name = "shop_id"))
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyColumn(name = "day")
    @Column(name = "closing_time")
    @NotNull
    //@JsonFormat(pattern = "hh:mm:ss")
    protected Map<DayOfWeek, LocalTime> closingTimes;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference
    protected List<Product> productList;

    public Shop() {}

    public Shop(String name, Boolean isOnLeave, Map<DayOfWeek, LocalTime> openingTimes , Map<DayOfWeek, LocalTime> closingTimes) {
        if (name.equals("")
                || isOnLeave == null
                || openingTimes.size() != DayOfWeek.values().length
                || closingTimes.size() != DayOfWeek.values().length) {
            throw new InvalidParameterException();
        }

        this.name = name;
        this.isOnLeave = isOnLeave;
        this.openingTimes = openingTimes;
        this.closingTimes = closingTimes;
        this.productList = new ArrayList<>();
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

    public Map<DayOfWeek, LocalTime> getOpeningTimes() {
        return this.openingTimes;
    }

    public Map<DayOfWeek, LocalTime> getClosingTimes() {
        return this.closingTimes;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

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

    public void setOpeningTime(DayOfWeek dayOfWeek, LocalTime openingTime) {
        if (dayOfWeek == null) {
            throw new InvalidParameterException();
        }
        openingTimes.put(dayOfWeek, openingTime);
    }

    public void setClosingTime(DayOfWeek dayOfWeek, LocalTime closingTime) {
        if (dayOfWeek == null) {
            throw new InvalidParameterException();
        }
        closingTimes.put(dayOfWeek, closingTime);
    }

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
