package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Shop;
import com.fullstack.fullstackproject.model.ShopRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("Get Shop List")
    public void getShopList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shop")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Create correct Shop")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void addCorrectShop() throws Exception {
        String name = "Burger Cringe";
        Boolean isOnLeave = false;

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.TUESDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.SATURDAY.name(), null);
        openingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String,  JSONArray>();
        closingTimes.put(DayOfWeek.MONDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.TUESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SATURDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));


        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes", new JSONObject(openingTimes));
        requestBody.put("closingTimes", new JSONObject(closingTimes));

        mockMvc.perform(MockMvcRequestBuilders.post("/shop")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Create Shop with incorrect name")
    public void addIncorrectShopWithIncorrectName() throws Exception {
        String name = "";
        String isOnLeave = "false";

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.TUESDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.SATURDAY.name(), null);
        openingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String,  JSONArray>();
        closingTimes.put(DayOfWeek.MONDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.TUESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SATURDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes", new JSONObject(openingTimes));
        requestBody.put("closingTimes", new JSONObject(closingTimes));

        mockMvc.perform(MockMvcRequestBuilders.post("/shop")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Create Shop with incorrect IsOnLeave")
    public void addIncorrectShopWithIncorrectIsOnLeave() throws Exception {
        String name = "Mc gros";
        String isOnLeave = "";

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.TUESDAY.name(), new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        openingTimes.put(DayOfWeek.SATURDAY.name(), null);
        openingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String,  JSONArray>();
        closingTimes.put(DayOfWeek.MONDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.TUESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.WEDNESDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.THURSDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.FRIDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SATURDAY.name(),  new JSONArray(new int[] {8, 30}));
        closingTimes.put(DayOfWeek.SUNDAY.name(),  new JSONArray(new int[] {8, 30}));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes", new JSONObject(openingTimes));
        requestBody.put("closingTimes", new JSONObject(closingTimes));

        mockMvc.perform(MockMvcRequestBuilders.post("/shop")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @DisplayName("Delete a present shop")
    public void deletePresentShop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shop/" + getLastShopId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("Delete a missing shop")
    public void deleteMissingShop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shop/" + (getLastShopId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Update shop correctly (1 parameter)")
    void correctlyUpdateShopWithOneParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update shop correctly (2 parameter)")
    void correctlyUpdateShopWithTwoParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update shop correctly (3 parameter)")
    void correctlyUpdateShopWithThreeParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";

        Map<String, JSONArray> openingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        Map<String, JSONArray> closingTimes = new HashMap<String, JSONArray>();
        openingTimes.put(DayOfWeek.MONDAY.name(), new JSONArray(new int[] {10, 30}));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("openingTimes",  new JSONObject(openingTimes));
        requestBody.put("closingTimes",  new JSONObject(closingTimes));
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update a none existent shop(1 parameter)")
    void updateNoneExistentShopWithOneParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shop/" + getLastShopId() + 1 )
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private Long getLastShopId() {
        Iterable<Shop> shops = shopRepository.findAll();
        Iterator<Shop> shopIterator = shops.iterator();

        Shop shop = null;
        while (shopIterator.hasNext()) {
            shop = shopIterator.next();
        }

        return shop != null ? shop.getId() : 1;
    }

}
