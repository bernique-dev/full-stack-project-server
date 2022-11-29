package com.fullstack.fullstackproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @CollectionTable(name = "translated_product",
                    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "FK_translation_to_product"),
                    joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyColumn(name = "language")
    @Column(name = "translation")
    protected Map<Language, ProductTranslation> translations;

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

    public ProductTranslation getTranslation(Language language) {
        if(language == null) {
            throw new InvalidParameterException();
        }
        return translations.get(language);
    }

    public Map<Language, ProductTranslation> getTranslations(){
        return translations;
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

    public void setTranslation(Language language, ProductTranslation productTranslation) {
        if(language == null || productTranslation == null) {
            throw new InvalidParameterException();
        }
        if (translations.containsKey(language)) {
            translations.get(language).setValues(productTranslation.getTranslatedName(), productTranslation.getTranslatedDescription());
        } else {
            translations.put(language, productTranslation);
        }
    }

    public void deleteTranslation(Language language) {
        if(language == null){
            throw new InvalidParameterException();
        }
        translations.remove(language);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    protected Product() {
        categories = new HashSet<>();
    }

    public Product(String name, float price, String description) {
        if (name == null || name.equals("") || price < 0) {
            throw new InvalidParameterException();
        }
        categories = new HashSet<>();
        this.name = name;
        this.price = price;
        this.description = description;
        this.translations = new HashMap<Language, ProductTranslation>();
    }

    @Override
    public String toString() {
        return name + " [" + price + "] (" + description + ")";
    }
}
