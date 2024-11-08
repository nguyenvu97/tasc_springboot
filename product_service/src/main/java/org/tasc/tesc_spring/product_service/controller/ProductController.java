package org.tasc.tesc_spring.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tasc.tesc_spring.product_service.model.Product;
import org.tasc.tesc_spring.product_service.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;

    @GetMapping("/get_all")
    public ResponseEntity<?>get_all(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "product_id") String sortBy){
        return ResponseEntity.ok().body(productService.selectProduct(pageNo,pageSize,sortBy));
    }

    @GetMapping("/get")
    public ResponseEntity<?>get(@RequestParam String product_id){
        return ResponseEntity.ok().body(productService.selectProductById(product_id));
    }
    @PostMapping
    public ResponseEntity<?>add(@RequestBody Product product){
        return ResponseEntity.ok().body(productService.insertProduct(product));
    }
    @DeleteMapping
    public ResponseEntity<?>delete(@RequestParam String product_id){
        return ResponseEntity.ok().body(productService.deleteProduct(product_id));
    }
}
