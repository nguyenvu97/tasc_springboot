package org.tasc.tesc_spring.product_service.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.tasc.tesc_spring.product_service.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("description")
        );
    }
}
