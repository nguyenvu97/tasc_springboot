package org.tasc.tesc_spring.product_service.dao.impl;

import lombok.RequiredArgsConstructor;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tasc.tasc_spring.api_common.ex.EntityNotFound;
import org.tasc.tasc_spring.api_common.model.ResponseData;
import org.tasc.tesc_spring.product_service.dao.ProductDao;
import org.tasc.tesc_spring.product_service.dto.ProductDto;
import org.tasc.tesc_spring.product_service.mapper.ProductMapper;
import org.tasc.tesc_spring.product_service.model.Product;
import org.tasc.tesc_spring.product_service.model.ProductStatus;
import org.tasc.tesc_spring.product_service.rowmapper.ProductRowMapper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDataAccessService implements ProductDao {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Product>  selectProduct(int offset, int limit, String sortBy) {
        String sql = "SELECT product_id," +
                " product_name, " +
                "product_description," +
                " product_price," +
                " product_quantity," +
                " path," +
                " img, " +
                "purchase_price," +
                "product_status," +
                "created_at," +
                "updated_at," +
                "category_id " +
                "FROM product ORDER BY "
                + sortBy +
                " LIMIT ? OFFSET ? ";
        return  jdbcTemplate.query(sql, new Object[]{limit, offset}, new ProductRowMapper());

    }

    @Override
    public int insertProduct(Product product) {
        String product_status = ProductStatus.OPEN.toString();
        LocalDateTime dateTime = LocalDateTime.now();

        String sql =  "INSERT INTO product (product_id, product_name, product_description, product_price, product_quantity, path, img, purchase_price,product_status,created_at,updated_at,category_id) VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                product.getProduct_name(),
                product.getProduct_description(),
                product.getProduct_price(),
                product.getProduct_quantity(),
                product.getPath(),
                product.getImg(),
                product.getPurchase_price(),
                product_status,
                dateTime.toString(),
                dateTime.toString(),
                product.getCategory_id()


        );


    }

    @Override
    public Product deleteProduct(String id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        Product product = jdbcTemplate.query(sql,new ProductRowMapper(),id).stream().findFirst().get();
        product.setProduct_status(ProductStatus.CLOSED.toString());
        jdbcTemplate.update(sql,id);
       return product;
    }

    @Override
    public Product selectProductById(String id)  {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), id).stream().findFirst().orElseThrow(() -> new EntityNotFound("not found",404));
    }

}
