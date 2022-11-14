package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Category;
import com.fullstack.fullstackproject.model.CategoryRepository;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Get Categories")
    public void getAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Add complete category")
    public void addCompleteCategory() throws Exception {
        String productName = "Literie";
        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .param("name", productName))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Add incomplete category")
    public void addIncompleteCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/categories"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @DisplayName("Delete correct product")
    public void deleteCorrectProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + getLastCategoryId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("Delete incorrect product")
    public void deleteIncorrectProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + (getLastCategoryId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Update category correctly")
    void correctlyUpdateCategory() throws Exception {
        String categoryName = "Linge";
        mockMvc.perform(MockMvcRequestBuilders.patch("/categories/" + getLastCategoryId())
                        .param("name", categoryName))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update category incorrectly")
    void incorrectlyUpdateCategory() throws Exception {
        String categoryName = "";
        mockMvc.perform(MockMvcRequestBuilders.patch("/categories/" + getLastCategoryId())
                        .param("name", categoryName))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update category without parameter")
    void updateCategoryWithoutParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/categories/" + getLastCategoryId()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update missing category")
    void updateMissingCategory() throws Exception {
        String categoryName = "";
        mockMvc.perform(MockMvcRequestBuilders.patch("/categories/" + (getLastCategoryId() + 1))
                        .param("name", categoryName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private Long getLastCategoryId() {
        Iterable<Category> categories = categoryRepository.findAll();
        Iterator<Category> categoryIterator = categories.iterator();

        Category category = categoryIterator.next();
        while (categoryIterator.hasNext()) {
            category = categoryIterator.next();
        }

        return category.getId();
    }

}