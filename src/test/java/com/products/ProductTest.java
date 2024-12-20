package com.products;
import com.products.models.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductModel() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Product product = new Product();

        // Act
        product.setId(1L);
        product.setName("Test Product");
        product.setCode("TEST001");
        product.setPrice(99.99);
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

        // Assert
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("TEST001", product.getCode());
        assertEquals(99.99, product.getPrice());
        assertEquals(now, product.getCreatedAt());
        assertEquals(now, product.getUpdatedAt());
    }

    @Test
    void testAllArgsConstructorAndToString() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();

        // Act
        Product product = new Product(1L, "Test Product", "TEST001", 99.99, now, now);

        // Assert
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("TEST001", product.getCode());
        assertEquals(99.99, product.getPrice());
        assertEquals(now, product.getCreatedAt());
        assertEquals(now, product.getUpdatedAt());
        assertNotNull(product.toString()); // Ensures toString does not return null
    }
}
