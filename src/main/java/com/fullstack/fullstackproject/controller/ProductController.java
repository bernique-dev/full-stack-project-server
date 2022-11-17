package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Product;
import com.fullstack.fullstackproject.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    public ProductRepository productRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Product>> getProducts() {
        return ResponseEntity.ok().body(productRepository.findAll());
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
            productRepository.save(productToUpdate);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
