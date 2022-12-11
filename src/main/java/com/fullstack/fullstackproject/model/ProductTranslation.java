package com.fullstack.fullstackproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.security.InvalidParameterException;

@Entity
@Table(name = "translation")
@JsonSerialize(using = ProductTranslationSerializer.class)
@JsonDeserialize(using = ProductTranslationDeserializer.class)
public class ProductTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "translated_name")
    @NotNull
    protected String translatedName;

    @Column(name = "translated_description")
    @NotNull
    protected String translatedDescription;

    public ProductTranslation() {}

    public ProductTranslation(String translatedName, String translatedDescription){
        this.setTranslatedName(translatedName);
        this.setTranslatedDescription(translatedDescription);
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public String getTranslatedDescription() {
        return translatedDescription;
    }


    public void setTranslatedName(String translatedName) {
        if (translatedName.equals("")) {
            throw new InvalidParameterException();
        }
        this.translatedName = translatedName;
    }

    public void setTranslatedDescription(String translatedDescription) {
        this.translatedDescription = translatedDescription;
    }

    public void setValues(String translatedName, String translatedDescription) {
        setTranslatedName(translatedName);
        setTranslatedDescription(translatedDescription);
    }

    @Override
    public String toString() {
        return translatedName + ": " + translatedDescription;
    }
}
