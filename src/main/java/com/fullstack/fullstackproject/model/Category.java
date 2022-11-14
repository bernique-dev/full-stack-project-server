package com.fullstack.fullstackproject.model;

import javax.persistence.*;
import java.security.InvalidParameterException;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    protected String name;

    protected Category() {}

    public Category(String name) {
        if (name.equals("")) {
            throw new InvalidParameterException();
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new InvalidParameterException();
        }
        this.name = name;
    }

}
