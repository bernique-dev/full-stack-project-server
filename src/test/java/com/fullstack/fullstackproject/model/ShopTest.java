package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ShopTest {

    @Test
    @DisplayName("Constructor with valid parameters")
    void createCorrectConstructor() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;
        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        Shop shop = new Shop(name, isOnLeave, horaries, LocalDate.now());
        assertEquals(shop.getName(), name);
        assertEquals(shop.getIsOnLeave(), isOnLeave);
        assertEquals(shop.getOpeningTimes(), horaries);
    }

    @Test
    @DisplayName("Constructor with incorrect name")
    void createIncorrectConstructorWithIncorrectName() {
        String name= "";
        Boolean isOnLeave = false;

        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        assertThrows(InvalidParameterException.class,() -> new Shop(name, isOnLeave, horaries, LocalDate.now()));
    }

    @Test
    @DisplayName("Constructor with incorrect isOnLeave")
    void createIncorrectConstructorWithIncorrectIsOnLeave() {
        String name= "Mc gros";
        Boolean isOnLeave = null;
        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        assertThrows(InvalidParameterException.class, () -> new Shop(name, isOnLeave, horaries, LocalDate.now()));
    }

    @Test
    @DisplayName("Constructor with incorrect horaries")
    void createIncorrectConstructorWithIncorrectHoraries() {
        String name= "Mc gros";
        Boolean isOnLeave = true;

        String horaries = "";

        assertThrows(InvalidParameterException.class, () -> new Shop(name, isOnLeave, horaries, LocalDate.now()));
    }


    @Test
    @DisplayName("Set correct name")
    void setCorrectName() {
        String name = "Burger Cringe";
        Shop shop = new Shop();
        shop.setName(name);
        assertEquals(shop.getName(), name);
    }

    @Test
    @DisplayName("Don't set incorrect name")
    void setIncorrectName() {
        String name = "";
        Shop shop = new Shop();
        assertThrows(InvalidParameterException.class, () -> shop.setName(name));
    }

    @Test
    @DisplayName("Set correct isOnLeave")
    void setCorrectIsOnLeave() {
        Boolean isOnLeave = false;
        Shop shop = new Shop();
        shop.setIsOnLeave(isOnLeave);
        assertFalse(shop.getIsOnLeave());
    }

    @Test
    @DisplayName("Don't set incorrect isOnLeave")
    void setIncorrectIsOnLeave() {
        Boolean isOnLeave = null;
        Shop shop = new Shop();
        assertThrows(InvalidParameterException.class, () -> shop.setIsOnLeave(isOnLeave));
    }



    @Test
    @DisplayName("add correct product")
    void addCorrectProduct(){
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        Shop shop = new Shop(name, isOnLeave, horaries, LocalDate.now());
        Product product = new Product();

        shop.addProduct(product);

        assertTrue(shop.getProductList().contains(product));
    }

    @Test
    @DisplayName("add incorrect product")
    void addIncorrectProduct(){
        Product product = null;
        Shop shop = new Shop();

        assertThrows(InvalidParameterException.class, () -> shop.addProduct(product));
    }

    @Test
    @DisplayName("delete existing product")
    void deleteExistentProduct() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        Shop shop = new Shop(name, isOnLeave, horaries, LocalDate.now());
        Product product = new Product();

        shop.addProduct(product);
        shop.deleteProduct(product);

        assertFalse(shop.getProductList().contains(product));
    }

    @Test
    @DisplayName("delete non-existing product")
    void deleteNonExistentProduct() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        String horaries = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        Shop shop = new Shop(name, isOnLeave, horaries, LocalDate.now());

        List<Product> saveList = shop.getProductList();
        Product product = new Product();
        shop.deleteProduct(product);

        assertEquals(shop.getProductList(), saveList);
    }

}