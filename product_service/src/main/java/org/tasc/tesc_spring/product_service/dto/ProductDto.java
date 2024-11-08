package org.tasc.tesc_spring.product_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private String product_id;
    private String product_name;
    private String product_description;
    private double product_price;
    private int product_quantity;
    private String path;
    private String img;
    private double purchase_price;
    private String product_status;
    private String category_name;
    private String category_description;
}
