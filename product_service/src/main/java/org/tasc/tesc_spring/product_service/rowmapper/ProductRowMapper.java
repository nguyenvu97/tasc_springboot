package org.tasc.tesc_spring.product_service.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.tasc.tesc_spring.product_service.model.Product;
import org.tasc.tesc_spring.product_service.model.ProductStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        String productIdString = rs.getString("product_id");
        UUID productId = null;
        if (productIdString != null) {
            productId = UUID.fromString(productIdString);
        }

        return new Product(
                productId,
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("product_price"),
                rs.getInt("product_quantity"),
                rs.getString("path"),
                rs.getString("img"),
                rs.getDouble("purchase_price"),
                rs.getString("product_status"),
                rs.getString("created_at"),
                rs.getString("updated_at"),
                rs.getInt("category_id")
        );
    }
}
