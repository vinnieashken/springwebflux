package com.products.controllers;

import com.products.models.Product;
import com.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public Mono<List<Product>> getProducts() {
        return productService.getProducts().collectList();
    }

    @PostMapping("add")
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
