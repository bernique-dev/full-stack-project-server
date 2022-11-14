package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Constructor with correct name")
    public void createCorrectNameConstructor() {
        String categoryName = "Livres";
        Category category = new Category(categoryName);
        assertEquals(category.getName(), categoryName);
    }

    @Test
    @DisplayName("Constructor with incorrect name")
    public void createIncorrectNameConstructor() {
        String categoryName = "";
        assertThrows(InvalidParameterException.class,() -> new Category(categoryName));
    }

    @Test
    @DisplayName("Sets correct name")
    public void setCorrectName() {
        String categoryName = "Livres";
        Category category = new Category();
        category.setName(categoryName);
        assertEquals(category.getName(), categoryName);
    }

    @Test
    @DisplayName("Sets incorrect name")
    public void setIncorrectName() {
        String categoryName = "";
        Category category = new Category();
        assertThrows(InvalidParameterException.class,() -> category.setName(categoryName));
    }
}