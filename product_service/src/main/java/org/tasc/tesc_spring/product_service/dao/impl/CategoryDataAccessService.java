package org.tasc.tesc_spring.product_service.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.tasc.tasc_spring.api_common.ex.EntityNotFound;
import org.tasc.tesc_spring.product_service.dao.CategoryDao;
import org.tasc.tesc_spring.product_service.model.Category;
import org.tasc.tesc_spring.product_service.rowmapper.CategoryRowMapper;
import org.tasc.tesc_spring.product_service.rowmapper.ProductRowMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDataAccessService implements CategoryDao {
public final JdbcTemplate  jdbcTemplate;
    @Override
    public Category selectProductById(int id) {
        String sql = "SELECT * FROM category WHERE category_id = ?";
        return jdbcTemplate.query(sql, new CategoryRowMapper(), id).stream().findFirst().orElseThrow(() -> new EntityNotFound("not found",404));
    }

    @Override
    public void insertProduct(List<Category> category) {
        for (Category categoryItem : category) {
            String sql = "INSERT INTO category (category_name,description) VALUES (?, ?)";
             jdbcTemplate.update(sql,categoryItem.getCategory_name(),categoryItem.getDescription());
        }

    }
}
