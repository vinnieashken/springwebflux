package com.products.repositories;

import com.products.models.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductsRepository extends ReactiveCrudRepository<Product, Long> {

}
