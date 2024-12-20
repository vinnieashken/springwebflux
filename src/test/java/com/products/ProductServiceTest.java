package com.products;

import com.products.services.ProductService;
import org.junit.jupiter.api.Test;

import com.products.models.Product;
import com.products.repositories.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productsRepository);
    }

    @Test
    void testGetProducts() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(2L, "Product 2", "CODE2", 20.0, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productsRepository.findAll()).thenReturn(Flux.just(product1, product2));

        // Act & Assert
        StepVerifier.create(productService.getProducts())
                .expectNext(product1)
                .expectNext(product2)
                .verifyComplete();

        // Verify
        Mockito.verify(productsRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testAddProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productsRepository.save(product)).thenReturn(Mono.just(product));

        // Act & Assert
        StepVerifier.create(productService.addProduct(product))
                .expectNext(product)
                .verifyComplete();

        // Verify
        Mockito.verify(productsRepository, Mockito.times(1)).save(product);
    }
}
