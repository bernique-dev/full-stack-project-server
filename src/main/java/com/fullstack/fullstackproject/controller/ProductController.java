package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController {

    @Autowired
    public ProductTranslationRepository productTranslationRepository;
    @Autowired
    public ShopRepository shopRepository;
    @Autowired
    public ProductRepository productRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Product>> getProducts(@RequestParam("category") Optional<Long> categoryId,
                                                         @RequestParam("shop") Optional<Long> shopId) {
        List<Product> products = new ArrayList<>();

        Iterable<Product> productsIterable;
        if (shopId.isPresent()) {
            Optional<Shop> shop = shopRepository.findById(shopId.get());
            if (shop.isPresent()) {
                productsIterable = shop.get().getProductList();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            productsIterable = productRepository.findAll();
        }

        if (categoryId.isPresent()) {

            for (Product product : productsIterable) {
                for (Category category : product.getCategories()) {
                    if (category.getId().equals(categoryId.get())) {
                        System.out.println("ID="+category.getId());
                        products.add(product);
                        break;
                    }
                }
            }
        } else {
            productsIterable.forEach(products::add);
        }
        return ResponseEntity.ok().body(products);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(optionalProduct.get());
    }

    @PostMapping("")
    public ResponseEntity<Long> addProduct(@RequestBody @Valid Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        boolean canBeRemoved = productRepository.findById(id).isPresent();

        if (canBeRemoved) {
            productRepository.deleteById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateById(@PathVariable("id") Long id, @RequestBody Product product) {
        boolean doesProductExist = productRepository.findById(id).isPresent();

        if (doesProductExist) {
            Product productToUpdate = productRepository.findById(id).get();
            if (product.getName() != null) productToUpdate.setName(product.getName());
            if (product.getPrice() != null) productToUpdate.setPrice(product.getPrice());
            if (product.getDescription() != null) productToUpdate.setDescription(product.getDescription());
            if (product.getCategories() != null) productToUpdate.setCategories(product.getCategories());
            if (product.getTranslations() != null) {
                for (Map.Entry<Language, ProductTranslation> couple: product.getTranslations().entrySet()) {
                    if(couple.getValue() != null) {
                        productToUpdate.setTranslation(couple.getKey(), couple.getValue());
                        productTranslationRepository.save(productToUpdate.getTranslation(couple.getKey()));
                    } else {
                        productToUpdate.deleteTranslation(couple.getKey());
                    }
                }
            }
            productRepository.save(productToUpdate);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
