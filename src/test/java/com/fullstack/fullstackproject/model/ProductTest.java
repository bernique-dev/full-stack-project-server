package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ProductTest {

    @Test
    @DisplayName("Constructor with correct parameters")
    void createCorrectConstructor() {
        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);
        assertEquals(product.getName(), productName);
        assertEquals(product.getPrice(), productPrice);
        assertEquals(product.getDescription(), productDescription);
    }

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
    @DisplayName("set correct couple Language/productTranslation")
    void addCorrectCoupleLanguageProductTranslation() {
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Master chicken","a delicious master with his chicken");

        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);

        product.setTranslation(language, productTranslation);

        assertEquals(product.getTranslation(language), productTranslation);
    }

    @Test
    @DisplayName("set incorrect couple Language/productTranslation")
    void addIncorrectCoupleLanguageProductTranslationWithIncoorectLanguage() {
        Language language =null;
        ProductTranslation productTranslation = new ProductTranslation("Master chicken","a delicious master with his chicken");

        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);

        assertThrows(InvalidParameterException.class,()->product.setTranslation(language, productTranslation));
    }

    @Test
    @DisplayName("set incorrect couple Language/productTranslation")
    void addIncorrectCoupleLanguageProductTranslationWithIncoorectProductTranslation() {
        Language language = Language.EN;

        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);

        assertThrows(InvalidParameterException.class,()->product.setTranslation(language, null));
    }

    @Test
    @DisplayName("delete existant couple Language/productTranslation")
    void deleteExistingCoupleLanguageProductTranslation() {
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Master chicken","a delicious master with his chicken");

        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);

        product.setTranslation(language, productTranslation);
        product.deleteTranslation(language);

        assertNull(product.getTranslation(language));
    }

    @Test
    @DisplayName("delete none existant couple Language/productTranslation")
    void deleteNoneExistingCoupleLanguageProductTranslation() {
        Language language = Language.EN;

        String productName = "Petit PC";
        float productPrice = 1.99f;
        String productDescription = "Tout petit PC pas très performant mais conviendra à un enfant en bas âge";
        Product product = new Product(productName, productPrice, productDescription);

        product.deleteTranslation(language);

        assertNull(product.getTranslation(language));
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