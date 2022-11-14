package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Category;
import com.fullstack.fullstackproject.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Long> addCategory(@RequestParam String name) {
        if (!name.equals("")) {
            Category createdCategory = new Category(name);
            categoryRepository.save(createdCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory.getId());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<Long> updateById(@PathVariable("id") Long id,
                                           @RequestParam Optional<String> name) {
        boolean doesProductExist = categoryRepository.findById(id).isPresent();

        if (doesProductExist) {
            if (name.isPresent() && !name.get().equals("")) {
                Category category = categoryRepository.findById(id).get();
                name.ifPresent(category::setName);
                categoryRepository.save(category);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
