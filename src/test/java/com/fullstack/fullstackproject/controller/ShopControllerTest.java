package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Shop;
import com.fullstack.fullstackproject.model.ShopRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Iterator;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/shops")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Create correct Shop")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void addCorrectShop() throws Exception {
        String name = "Burger Cringe";
        Boolean isOnLeave = false;
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";


        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("schedule", schedule);

        mockMvc.perform(MockMvcRequestBuilders.post("/shops")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Create Shop with incorrect name")
    public void addIncorrectShopWithIncorrectName() throws Exception {
        String name = "";
        String isOnLeave = "false";
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("schedule", schedule);

        mockMvc.perform(MockMvcRequestBuilders.post("/shops")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Create Shop with incorrect IsOnLeave")
    public void addIncorrectShopWithIncorrectIsOnLeave() throws Exception {
        String name = "Mc gros";
        String isOnLeave = "";
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("schedule", schedule);

        mockMvc.perform(MockMvcRequestBuilders.post("/shops")
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @DisplayName("Delete a present shop")
    public void deletePresentShop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shops/" + getLastShopId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("Delete a missing shop")
    public void deleteMissingShop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shops/" + (getLastShopId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Update shop correctly (1 parameter)")
    void correctlyUpdateShopWithOneParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";
        
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("schedule",  schedule);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update shop correctly (2 parameter)")
    void correctlyUpdateShopWithTwoParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("schedule",  schedule);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        requestBody = new JSONObject();
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("schedule",  schedule);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update shop correctly (3 parameter)")
    void correctlyUpdateShopWithThreeParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";
        String isOnLeave = "false";
        String schedule = ";08:45-18:15;09:00-19:15;08:30-17:15;10:00-18:15;;";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("isOnLeave", isOnLeave);
        requestBody.put("schedule",  schedule);

        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId())
                        .content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update a non-existing shop(1 parameter)")
    void updateNonExistingShopWithOneParameter() throws Exception {
        String name = "KFC (Ketchup Frites Caramel)";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        mockMvc.perform(MockMvcRequestBuilders.patch("/shops/" + getLastShopId() + 1 )
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
