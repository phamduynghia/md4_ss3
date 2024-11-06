package com.ra.demo3.service.category;

import com.ra.demo3.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    void delete(Long id);
}
