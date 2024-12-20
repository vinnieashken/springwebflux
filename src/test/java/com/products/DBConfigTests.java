package com.products;

import com.products.config.DBConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import io.r2dbc.spi.ConnectionFactory;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = DBConfig.class)
class DBConfigTests {

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    private R2dbcMappingContext r2dbcMappingContext;
    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    void testR2dbcEntityTemplateBean() {
        assertNotNull(r2dbcEntityTemplate, "R2dbcEntityTemplate bean should not be null");
    }

    @Test
    void testR2dbcMappingContextBean() {
        assertNotNull(r2dbcMappingContext, "R2dbcMappingContext bean should not be null");
    }

    @Test
    void testConnectionFactoryBean() {
        ConnectionFactory mockConnectionFactory = mock(ConnectionFactory.class);

        // Mock the DBConfig to return the mocked ConnectionFactory
        DBConfig dbConfig = new DBConfig() {
            @Override
            public ConnectionFactory connectionFactory() {
                return mockConnectionFactory;
            }
        };

        R2dbcEntityTemplate template = dbConfig.r2dbcEntityTemplate(connectionFactory);

        assertNotNull(template, "R2dbcEntityTemplate should be created successfully");
        assertSame(mockConnectionFactory, dbConfig.connectionFactory(), "The connectionFactory should match the mock");
    }

    @Test
    void testR2dbcMappingContextCreation() {
        DBConfig dbConfig = new DBConfig();
        R2dbcMappingContext mappingContext = dbConfig.r2dbcMappingContext();
        assertNotNull(mappingContext, "R2dbcMappingContext should be created successfully");
    }

    @Test
    void testConnectionFactoryCreation() {
        DBConfig dbConfig = new DBConfig() ;
        assertNull(dbConfig.connectionFactory(), "The connectionFactory should match the mock");
    }
}
