package com.fullstack.fullstackproject.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);

}
