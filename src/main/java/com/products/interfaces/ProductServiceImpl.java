package com.products.interfaces;

import com.products.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductServiceImpl {

    public Flux<Product> getProducts();
    public Mono<Product> addProduct(Product product);
}
