package com.fullstack.fullstackproject.controllers;

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
    @DisplayName("Add incorrect product")
    public void addIncorrectProduct() throws Exception {
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
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> productIterator = products.iterator();

        Product product = productIterator.next();
        while (productIterator.hasNext()) {
            product = productIterator.next();
        }

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + product.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("Delete incorrect product")
    public void deleteIncorrectProduct() throws Exception {
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> productIterator = products.iterator();

        Product product = productIterator.next();
        while (productIterator.hasNext()) {
            product = productIterator.next();
        }

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + (product.getId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
