package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Product;
import com.fullstack.fullstackproject.model.ProductRepository;
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
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Get Products")
    public void getProductsList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Add correct and complete product")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void addCorrectProduct() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("name", productName)
                        .param("price", productPrice)
                        .param("description", productDescription))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Add incomplete product")
    public void addIncompleteProduct() throws Exception {
        String productName = "Poêle Fetal";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("name", productName)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add incorrect product (Incorrect name)")
    public void addIncorrectProductWithIncorrectName() throws Exception {
        String productName = "";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("name", productName)
                        .param("price", productPrice)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add incorrect product (Incorrect price)")
    public void addIncorrectProductWithIncorrectPrice() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "-15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("name", productName)
                        .param("price", productPrice)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @DisplayName("Delete correct product")
    public void deleteCorrectProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + getLastProductId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("Delete unknown product")
    public void deleteIncorrectProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + (getLastProductId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Update product correctly (1 parameter)")
    void correctlyUpdateProductWithOneParameter() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("name", productName))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("price", productPrice))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update product correctly (2 parameters)")
    void correctlyUpdateProductWithTwoParameters() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("name", productName)
                        .param("price", productPrice))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("price", productPrice)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("name", productName)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Update product correctly (3 parameters)")
    void correctlyUpdateProductWithThreeParameters() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("name", productName)
                        .param("price", productPrice)
                        .param("description", productDescription))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update product incorrectly (Incorrect name)")
    void updateProductWithIncorrectName() throws Exception {
        String productName = "";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("name", productName))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update product incorrectly (Incorrect price)")
    void updateProductWithIncorrectPrice() throws Exception {
        String productPrice = "-14.99";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .param("price", productPrice))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    @DisplayName("Update product without parameter")
    void updateProductWithoutParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update missing product")
    void updateMissingProduct() throws Exception {
        String productName = "Boussole Ini";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + (getLastProductId() + 1))
                        .param("name", productName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private Long getLastProductId() {
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> productIterator = products.iterator();

        Product product = productIterator.next();
        while (productIterator.hasNext()) {
            product = productIterator.next();
        }

        return product.getId();
    }

}
