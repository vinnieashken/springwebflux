package com.products.services;

import com.products.interfaces.ProductServiceImpl;
import com.products.models.Product;
import com.products.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements ProductServiceImpl {


    ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Flux<Product> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Mono<Product> addProduct(Product product) {
        return productsRepository.save(product);
    }

}
