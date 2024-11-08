package org.tasc.tesc_spring.product_service.dao;

import org.tasc.tasc_spring.api_common.model.ResponseData;
import org.tasc.tesc_spring.product_service.dto.ProductDto;
import org.tasc.tesc_spring.product_service.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectProduct(int offset, int limit, String sortBy);
    int insertProduct(Product  product);
    Product deleteProduct(String id);
    Product selectProductById(String id);
}
