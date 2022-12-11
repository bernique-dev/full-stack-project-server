package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTranslationTest {

    @Test
    @DisplayName("Constructor with valid parameters")
    void createCorrectProductTranslation() {
        String translatedName = "Master chicken";
        String translatedDescription = "A delicious master and his chicken";

        ProductTranslation prodTranslate = new ProductTranslation(translatedName,translatedDescription);
        assertEquals(prodTranslate.getTranslatedName(),translatedName);
        assertEquals(prodTranslate.getTranslatedDescription(),translatedDescription);
    }

    @Test
    @DisplayName("Constructor with invalid parameters")
    void createIncorrectProductTranslationWithIncorrectName() {
        String translatedName = "";
        String translatedDescription = "un délicieux burger";

        assertThrows(InvalidParameterException.class,() -> new ProductTranslation(translatedName, translatedDescription));
    }

    @Test
    @DisplayName("set correct translatedName")
    void createCorrectTranslatedName() {
        String translatedName = "Master chicken";
        String newName = "Master chicken !!!";
        String translatedDescription = "A delicious master and his chicken";

        ProductTranslation prodTranslate = new ProductTranslation(translatedName,translatedDescription);
        prodTranslate.setTranslatedName(newName);
        assertEquals(prodTranslate.getTranslatedName(),newName);

    }

    @Test
    @DisplayName("set incorrect translatedName")
    void createIncorrectTranslatedName() {
        String translatedName = "Master chicken";
        String newName = "";
        String translatedDescription = "A delicious master and his chicken";

        ProductTranslation prodTranslate = new ProductTranslation(translatedName,translatedDescription);
        assertThrows(InvalidParameterException.class,() -> prodTranslate.setTranslatedName(newName));

    }

    @Test
    @DisplayName("set correct translatedDescription")
    void createCorrectTranslatedDescription() {
        String translatedName = "Master chicken";
        String translatedDescription = "A delicious master and his chicken";
        String newDescription = "A delicious master with tomato and his chicken";

        ProductTranslation prodTranslate = new ProductTranslation(translatedName,translatedDescription);
        prodTranslate.setTranslatedDescription(newDescription);
        assertEquals(prodTranslate.getTranslatedDescription(),newDescription);

    }
}
