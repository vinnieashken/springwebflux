package com.products;

import com.products.config.DBConfig;
import com.products.config.SecurityConfig;
import com.products.controllers.ProductsController;
import com.products.models.Product;
import com.products.repositories.ProductsRepository;
import com.products.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;



@WebFluxTest(ProductsController.class)
@ExtendWith(SpringExtension.class)
@Import({SecurityConfig.class, DBConfig.class})
public class ProductsControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Mock
    private R2dbcMappingContext r2dbcMappingContext;  // Mock R2dbcMappingContext

    @Mock
    private R2dbcEntityTemplate r2dbcEntityTemplate;  // Mock R2dbcEntityTemplate

    @Mock
    private ProductsRepository productsRepository;  // Mock ProductsRepository

    @MockitoBean
    private ProductService productService;  // Mock ProductService directly

    @InjectMocks
    private ProductsController productsController;  // Inject mocks into the controller

    @Test
    void testGetProducts() {
        List<Product> products = List.of(
                new Product(1L, "Product 1", "P001", 10.0, LocalDateTime.now(), LocalDateTime.now()),
                new Product(2L, "Product 2", "P002", 15.0, LocalDateTime.now(), LocalDateTime.now())
        );

        Mockito.when(productService.getProducts()).thenReturn(Flux.fromIterable(products));

        webTestClient.get()
                .uri("/api/products/list")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(2)
                .contains(products.get(0), products.get(1));
    }

    @Test
    void testAddProduct(){
        Product product = new Product(null, "New Product", "P003", 20.0, null, null);
        Product savedProduct = new Product(3L, "New Product", "P003", 20.0, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(Mono.just(savedProduct));

        webTestClient.post()
                .uri("/api/products/add")
                .bodyValue(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(savedProduct);
    }
}
