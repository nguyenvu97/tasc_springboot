package org.tasc.tesc_spring.product_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tasc.tasc_spring.api_common.ex.EntityNotFound;
import org.tasc.tasc_spring.api_common.model.ResponseData;
import org.tasc.tesc_spring.product_service.config.ConfigApp;
import org.tasc.tesc_spring.product_service.dao.CategoryDao;
import org.tasc.tesc_spring.product_service.dao.ProductDao;
import org.tasc.tesc_spring.product_service.dto.ProductDto;
import org.tasc.tesc_spring.product_service.mapper.ProductMapper;
import org.tasc.tesc_spring.product_service.model.Category;
import org.tasc.tesc_spring.product_service.model.Product;
import org.tasc.tesc_spring.product_service.service.ProductService;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final ConfigApp configApp;
    private final ProductMapper productMapper;
    private final CategoryDao categoryDao;

    @Override
    public ResponseData selectProduct(int pageNo, int pageSize, String sortBy) {
        if (pageNo <=0 || pageSize <= 0) {
            pageSize = configApp.pageSize;
            pageNo = configApp.pageNumber;
        }
        int offset = pageNo* pageSize;
        List<ProductDto> productDtos = null;
        List<Product> product = productDao.selectProduct(offset, pageSize, sortBy);
        productDtos = product.stream().map(s ->{
            Category category = categoryDao.selectProductById(s.getCategory_id());
         return  productMapper.toEntity(s,category.getCategory_name(),category.getDescription());
        }
        ).collect(Collectors.toList());
        return ResponseData
                .builder()
                .data(productDtos)
                .status_code(200)
                .message("ok")
                .build();
    }

    @Override
    public ResponseData insertProduct(Product product) {
       int data =  productDao.insertProduct(product);
       if (data == 0){
           throw new EntityNotFound("not add product",404);
       }
       return ResponseData.builder()
               .status_code(200)
               .message("ok")
               .data("create_ok")
               .build();
    }

    @Override
    public ResponseData deleteProduct(String id) {
        Product data =  productDao.deleteProduct(id);
        if (data == null){
            throw new EntityNotFound("not add product",404);
        }
        return ResponseData
                .builder()
                .status_code(200)
                .message("ok")
                .data(data)
                .build();
    }

    @Override
    public ResponseData selectProductById(String id) {
        Product product = productDao.selectProductById(id);
        if (product == null) {
            throw new EntityNotFound("not found",404);
        }
        Category category = categoryDao.selectProductById(product.getCategory_id());
        return ResponseData
                .builder()
                .status_code(200)
                .message("ok")
                .data(productMapper.toEntity(product,category.getCategory_name(),category.getDescription()))
                .build();
    }
}
