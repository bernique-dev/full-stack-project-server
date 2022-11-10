package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Product;
import com.fullstack.fullstackproject.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Long> addProduct(@RequestParam String name, @RequestParam float price,
                                           @RequestParam String description) {
        if (!name.equals("") && price >= 0) {
            Product createdProduct = new Product(name, price, description);
            productRepository.save(createdProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct.getId());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<Long> updateById(@PathVariable("id") Long id,
                                           @RequestParam(required = false) Optional<String> name,
                                           @RequestParam(required = false) Optional<Float> price,
                                           @RequestParam(required = false) Optional<String> description) {
        boolean doesProductExist = productRepository.findById(id).isPresent();
        boolean hasBeenUpdated = false;

        if (doesProductExist) {
            boolean canBeUpdated = true;
            if (name.isPresent()) {
                canBeUpdated = !name.get().equals("");
                hasBeenUpdated = true;
            }
            if (price.isPresent()) {
                canBeUpdated = canBeUpdated && price.get() >= 0;
                hasBeenUpdated = true;
            }
            if (description.isPresent()) {
                hasBeenUpdated = true;
            }

            if (canBeUpdated && hasBeenUpdated) {
                Product product = productRepository.findById(id).get();
                //  condition(object::method)
                //  runs object.method() if condition is true
                name.ifPresent(product::setName);
                price.ifPresent(product::setPrice);
                description.ifPresent(product::setDescription);
                productRepository.save(product);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
