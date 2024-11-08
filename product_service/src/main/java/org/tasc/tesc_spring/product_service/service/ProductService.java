package org.tasc.tesc_spring.product_service.service;

import org.tasc.tasc_spring.api_common.model.ResponseData;
import org.tasc.tesc_spring.product_service.model.Product;

public interface ProductService  {

    ResponseData selectProduct(int pageNo, int pageSize, String sortBy);
    ResponseData insertProduct(Product product);

    ResponseData deleteProduct(String id);
    ResponseData selectProductById(String id);
}
