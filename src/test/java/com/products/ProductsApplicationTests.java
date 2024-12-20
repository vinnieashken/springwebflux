package com.products;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductsApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true, "The test should always pass as a placeholder");
	}

	@Test
	void testMainMethod() {
		try (var mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
			ProductsApplication.main(new String[]{});
			mockedSpringApplication.verify(() -> SpringApplication.run(ProductsApplication.class, new String[]{}));
		}
	}

}
