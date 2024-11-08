package org.tasc.tesc_spring.product_service.dao;

import org.tasc.tesc_spring.product_service.model.Category;

import java.util.List;

public interface CategoryDao {
    Category selectProductById(int id);
    void insertProduct(List<Category> category);
}
