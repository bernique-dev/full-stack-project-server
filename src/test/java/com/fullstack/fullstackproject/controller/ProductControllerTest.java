package com.fullstack.fullstackproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fullstack.fullstackproject.model.*;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;

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

        JSONObject json = new JSONObject();
        json.put("name", productName);
        json.put("price", productPrice);
        json.put("description", productDescription);

        JSONObject shopJson = new JSONObject();
        shopJson.put("id", shopRepository.findAll().iterator().next().getId());
        json.put("shop", shopJson);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Add incomplete product")
    public void addIncompleteProduct() throws Exception {
        String productName = "Poêle Fetal";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";

        JSONObject json = new JSONObject();
        json.put("name", productName);
        json.put("description", productDescription);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add incorrect product (Incorrect name)")
    public void addIncorrectProductWithIncorrectName() throws Exception {
        String productName = "";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";

        JSONObject json = new JSONObject();
        json.put("name", productName);
        json.put("price", productPrice);
        json.put("description", productDescription);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add incorrect product (Incorrect price)")
    public void addIncorrectProductWithIncorrectPrice() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "-15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";

        JSONObject json = new JSONObject();
        json.put("name", productName);
        json.put("price", productPrice);
        json.put("description", productDescription);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
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
    @DisplayName("Delete missing product")
    public void deleteMissingProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + (getLastProductId()+1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Update product correctly (1 parameter)")
    void correctlyUpdateProductWithOneParameter() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Lethal Pan", "A pan to kill them all");
        Map<Language, ProductTranslation> translationMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ProductTranslation.class, new ProductTranslationSerializer());
        module.addDeserializer(ProductTranslation.class, new ProductTranslationDeserializer());
        mapper.registerModule(module);

        translationMap.put(language, productTranslation);

        JSONObject jsonName = new JSONObject();
        jsonName.put("name", productName);
        JSONObject jsonPrice = new JSONObject();
        jsonPrice.put("price", productPrice);
        JSONObject jsonDescription = new JSONObject();
        jsonDescription.put("description", productDescription);
        JSONObject jsonTranslationMap = new JSONObject();
        jsonTranslationMap.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonName.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonPrice.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonDescription.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonTranslationMap.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update product correctly (2 parameters)")
    void correctlyUpdateProductWithTwoParameters() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Lethal Pan", "A pan to kill them all");
        Map<Language, ProductTranslation> translationMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ProductTranslation.class, new ProductTranslationSerializer());
        module.addDeserializer(ProductTranslation.class, new ProductTranslationDeserializer());
        mapper.registerModule(module);

        translationMap.put(language, productTranslation);

        JSONObject jsonWithoutDescription = new JSONObject();
        jsonWithoutDescription.put("name", productName);
        jsonWithoutDescription.put("price", productPrice);

        JSONObject jsonWithoutName = new JSONObject();
        jsonWithoutName.put("price", productPrice);
        jsonWithoutName.put("description", productDescription);

        JSONObject jsonWithoutPrice = new JSONObject();
        jsonWithoutPrice.put("name", productName);
        jsonWithoutPrice.put("description", productDescription);

        JSONObject jsonTranslationMapName = new JSONObject();
        jsonTranslationMapName.put("name", productName);
        jsonTranslationMapName.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));

        JSONObject jsonTranslationMapDescription = new JSONObject();
        jsonTranslationMapDescription.put("description", productDescription);
        jsonTranslationMapDescription.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));

        JSONObject jsonTranslationMapPrice = new JSONObject();
        jsonTranslationMapPrice.put("price", productPrice);
        jsonTranslationMapPrice.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutDescription.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutName.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutPrice.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonTranslationMapName.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonTranslationMapDescription.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonTranslationMapPrice.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Update product correctly (3 parameters)")
    void correctlyUpdateProductWithThreeParameters() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Lethal Pan", "A pan to kill them all");
        Map<Language, ProductTranslation> translationMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ProductTranslation.class, new ProductTranslationSerializer());
        module.addDeserializer(ProductTranslation.class, new ProductTranslationDeserializer());
        mapper.registerModule(module);

        translationMap.put(language, productTranslation);


        JSONObject jsonWithoutTranslation = new JSONObject();
        jsonWithoutTranslation.put("name", productName);
        jsonWithoutTranslation.put("price", productPrice);
        jsonWithoutTranslation.put("description", productDescription);
        jsonWithoutTranslation.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));

        JSONObject jsonWithoutName = new JSONObject();
        jsonWithoutName.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));
        jsonWithoutName.put("price", productPrice);
        jsonWithoutName.put("description", productDescription);

        JSONObject jsonWithoutPrice = new JSONObject();
        jsonWithoutPrice.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));
        jsonWithoutPrice.put("name", productName);
        jsonWithoutPrice.put("description", productDescription);


        JSONObject jsonWithoutDescription = new JSONObject();
        jsonWithoutDescription.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));
        jsonWithoutDescription.put("name", productName);
        jsonWithoutDescription.put("price", productPrice);



        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutTranslation.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutName.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutPrice.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonWithoutDescription.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update product correctly (4 parameters)")
    void correctlyUpdateProductWithFourParameters() throws Exception {
        String productName = "Poêle Fetal";
        String productPrice = "15.99";
        String productDescription = "Poêle parfaite pour les crêpes, gnocchis et ping-pong";
        Language language = Language.EN;
        ProductTranslation productTranslation = new ProductTranslation("Lethal Pan", "A pan to kill them all");
        Map<Language, ProductTranslation> translationMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ProductTranslation.class, new ProductTranslationSerializer());
        module.addDeserializer(ProductTranslation.class, new ProductTranslationDeserializer());
        mapper.registerModule(module);

        translationMap.put(language, productTranslation);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("translations", new JSONObject(mapper.writeValueAsString(translationMap)));
        jsonObject.put("name", productName);
        jsonObject.put("price", productPrice);
        jsonObject.put("description", productDescription);


        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Update product incorrectly (Incorrect name)")
    void updateProductWithIncorrectName() throws Exception {
        String productName = "";

        JSONObject json = new JSONObject();
        json.put("name", productName);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update product incorrectly (Incorrect price)")
    void updateProductWithIncorrectPrice() throws Exception {
        String productPrice = "-14.99";

        JSONObject json = new JSONObject();
        json.put("price", productPrice);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update product incorrectly (Incorrect Translation)")
    void updateProductWithIncorrectTranslation() throws Exception {


        String badTranslationObject = "{\"translations\": {\n" +
                "    \"EN\": {\n" +
                "      \"translatedName\": \"\",\n" +
                "      \"translatedDescription\": \"\"\n" +
                "    }}";

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + getLastProductId())
                        .content(badTranslationObject)
                        .contentType(MediaType.APPLICATION_JSON))
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

        JSONObject json = new JSONObject();
        json.put("name", productName);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + (getLastProductId() + 1))
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private Long getLastProductId() {
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> productIterator = products.iterator();

        Product product = null;
        while (productIterator.hasNext()) {
            product = productIterator.next();
        }

        return product != null ? product.getId() : 1;
    }

}
