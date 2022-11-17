package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Category;
import com.fullstack.fullstackproject.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity<Long> addCategory(@RequestBody @Valid Category category) {
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        boolean canBeRemoved = categoryRepository.findById(id).isPresent();

        if (canBeRemoved) {
            categoryRepository.deleteById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateById(@PathVariable("id") Long id, @RequestBody Category category) {
        boolean doesProductExist = categoryRepository.findById(id).isPresent();

        if (doesProductExist) {
            Category categoryToUpdate = categoryRepository.findById(id).get();
            if (category.getName() != null) categoryToUpdate.setName(category.getName());
            categoryRepository.save(categoryToUpdate);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
