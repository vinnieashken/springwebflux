package com.products;
import org.junit.jupiter.api.Test;

import com.products.models.Product;
import com.products.repositories.ProductsRepository;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
class ProductsRepositoryTest {

    @Mock
    private ProductsRepository productsRepository;

    @Test
    void contextLoads() {
        // Verifies that the Spring application context loads successfully.
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(2L, "Product 2", "CODE2", 20.0, LocalDateTime.now(), LocalDateTime.now());

        when(productsRepository.findAll()).thenReturn(Flux.just(product1, product2));

        // Act & Assert
        StepVerifier.create(productsRepository.findAll())
                .expectNext(product1)
                .expectNext(product2)
                .verifyComplete();

        // Verify
        verify(productsRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        // Arrange
        Product product = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());

        when(productsRepository.findById(1L)).thenReturn(Mono.just(product));

        // Act & Assert
        StepVerifier.create(productsRepository.findById(1L))
                .expectNext(product)
                .verifyComplete();

        // Verify
        verify(productsRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());

        when(productsRepository.save(product)).thenReturn(Mono.just(product));

        // Act & Assert
        StepVerifier.create(productsRepository.save(product))
                .expectNext(product)
                .verifyComplete();

        // Verify
        verify(productsRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", "CODE1", 10.0, LocalDateTime.now(), LocalDateTime.now());

        when(productsRepository.delete(product)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(productsRepository.delete(product))
                .verifyComplete();

        // Verify
        verify(productsRepository, times(1)).delete(product);
    }
}
