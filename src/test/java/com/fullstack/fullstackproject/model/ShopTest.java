package com.fullstack.fullstackproject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ShopTest {

    @Test
    @DisplayName("Constructor with valid parameters")
    void createCorrectConstructor() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());
        assertEquals(shop.getName(), name);
        assertEquals(shop.getIsOnLeave(), isOnLeave);
        assertEquals(shop.getOpeningTimes(), openingTimes);
        assertEquals(shop.getClosingTimes(), closingTimes);
    }

    @Test
    @DisplayName("Constructor with incorrect name")
    void createIncorrectConstructorWithIncorrectName() {
        String name= "";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        assertThrows(InvalidParameterException.class,() -> new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now()));
    }

    @Test
    @DisplayName("Constructor with incorrect isOnLeave")
    void createIncorrectConstructorWithIncorrectIsOnLeave() {
        String name= "Mc gros";
        Boolean isOnLeave = null;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        assertThrows(InvalidParameterException.class, () -> new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now()));
    }

    @Test
    @DisplayName("Constructor with incorrect openingTimes")
    void createIncorrectConstructorWithIncorrectOpeningTimes() {
        String name= "Mc gros";
        Boolean isOnLeave = true;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        assertThrows(InvalidParameterException.class, () -> new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now()));
    }

    @Test
    @DisplayName("Constructor with incorrect closingTimes")
    void createIncorrectConstructorWithIncorrectClosingTimes() {
        String name= "Mc gros";
        Boolean isOnLeave = true;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));

        assertThrows(InvalidParameterException.class, () -> new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now()));
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
    @DisplayName("set correct couple (dayofWeek,LocalTime) in openingTime")
    void setCorrectCoupleInOpeningTime() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());

        DayOfWeek day = DayOfWeek.WEDNESDAY;
        LocalTime time = LocalTime.of(8, 30);

        shop.setOpeningTime(day, time);

        assertTrue(shop.getOpeningTimes().containsKey(day) &&
                shop.getOpeningTimes().containsValue(time));
    }


    @Test
    @DisplayName("set incorrect couple (dayofWeek,LocalTime) in openingTime")
    void setIncorrectCoupleInOpeningTime() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());

        DayOfWeek day = null;
        LocalTime time = LocalTime.of(8, 30);

        assertThrows(InvalidParameterException.class, () -> shop.setClosingTime(day, time));
    }

    @Test
    @DisplayName("set correct couple (dayofWeek,LocalTime) in closingTimes")
    void setCorrectCoupleInClosingTime() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());

        DayOfWeek day = DayOfWeek.WEDNESDAY;
        LocalTime time = LocalTime.of(8, 30);

        shop.setClosingTime(day, time);

        assertTrue(shop.getClosingTimes().containsKey(day) &&
                shop.getClosingTimes().containsValue(time));
    }

    @Test
    @DisplayName("set incorrect couple (dayofWeek,LocalTime) in closingTimes")
    void setIncorrectCoupleInClosingTime() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());

        DayOfWeek day = null;
        LocalTime time = LocalTime.of(8, 30);

        assertThrows(InvalidParameterException.class, () -> shop.setOpeningTime(day, time));
    }

    @Test
    @DisplayName("add correct product")
    void addCorrectProduct(){
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());
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
    @DisplayName("delete existent product")
    void deleteExistentProduct() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());
        Product product = new Product();

        shop.addProduct(product);
        shop.deleteProduct(product);

        assertFalse(shop.getProductList().contains(product));
    }

    @Test
    @DisplayName("delete none existent product")
    void deleteNonExistentProduct() {
        String name= "Burger Cringe";
        Boolean isOnLeave = false;

        Map<DayOfWeek, LocalTime> openingTimes = new HashMap<DayOfWeek, LocalTime>();
        openingTimes.put(DayOfWeek.MONDAY, LocalTime.of(7, 30));
        openingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(8, 30));
        openingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30));
        openingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(10, 30));
        openingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(11, 30));
        openingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(12, 30));
        openingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(13, 30));

        Map<DayOfWeek, LocalTime> closingTimes = new HashMap<DayOfWeek, LocalTime>();
        closingTimes.put(DayOfWeek.MONDAY, LocalTime.of(17, 30));
        closingTimes.put(DayOfWeek.TUESDAY, LocalTime.of(18, 30));
        closingTimes.put(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30));
        closingTimes.put(DayOfWeek.THURSDAY, LocalTime.of(20, 30));
        closingTimes.put(DayOfWeek.FRIDAY, LocalTime.of(21, 30));
        closingTimes.put(DayOfWeek.SATURDAY, LocalTime.of(22, 30));
        closingTimes.put(DayOfWeek.SUNDAY, LocalTime.of(23, 30));

        Shop shop = new Shop(name, isOnLeave, openingTimes, closingTimes, LocalDate.now());

        List<Product> saveList = shop.getProductList();
        Product product = new Product();
        shop.deleteProduct(product);

        assertEquals(shop.getProductList(), saveList);
    }

}