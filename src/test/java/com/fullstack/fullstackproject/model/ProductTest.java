package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ProductTest {

    @Test
    @DisplayName("Constructor with incorrect price")
    void createIncorrectPriceConstructor() {
        String productName = "Petit PC";
        float productPrice = -1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        assertThrows(InvalidParameterException.class,() -> new Product(productName, productPrice, productDescription));
    }

    @Test
    @DisplayName("Constructor with incorrect name")
    void createIncorrectNameConstructor() {
        String productName = "";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        assertThrows(InvalidParameterException.class,() -> new Product(productName, productPrice, productDescription));
    }


    @Test
    @DisplayName("Set correct name")
    void setCorrectName() {
        String productName = "Petit PC";
        Product product = new Product();
        product.setName(productName);
        assertEquals(product.getName(), productName);
    }

    @Test
    @DisplayName("Don't set incorrect name")
    void setIncorrectName() {
        String productName = "";
        Product product = new Product();
        assertThrows(InvalidParameterException.class, () -> product.setName(productName));
    }

    @Test
    @DisplayName("Set correct price")
    void setCorrectPrice() {
        float productPrice = 1.99f;
        Product product = new Product();
        product.setPrice(productPrice);
        assertEquals(product.getPrice(), productPrice);
    }
    @Test
    @DisplayName("Don't set incorrect price")
    void setIncorrectPrice() {
        float productPrice = -0.99f;
        Product product = new Product();

        assertThrows(InvalidParameterException.class, () -> product.setPrice(productPrice));
    }

    @Test
    @DisplayName("Correct toString")
    void testToString() {
        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);
        assertEquals(product.toString(), productName + " [" + productPrice + "] (" + productDescription + ")");
    }
}