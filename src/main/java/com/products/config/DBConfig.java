package com.products.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcAuditing
public class DBConfig extends AbstractR2dbcConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(Environment environment) {
        return ConnectionFactories.get(ConnectionFactoryOptions
                .builder()
                .option(DRIVER, environment.getProperty("spring.r2dbc.driver", "mysql"))
                .option(HOST, environment.getProperty("spring.r2dbc.url.host", "localhost")) // Default to localhost
                .option(PORT, Integer.parseInt(environment.getProperty("spring.r2dbc.url.port", "3306"))) // Default to 3306
                .option(USER, environment.getProperty("spring.r2dbc.username", "root")) // Default to root
                .option(PASSWORD, environment.getProperty("spring.r2dbc.password", "")) // Default to empty password
                .option(DATABASE, environment.getProperty("spring.r2dbc.url.database", "springr2dbc")) // Default database
                .build());
    }

    @Bean
    ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        return initializer;
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

    @Bean
    public R2dbcMappingContext r2dbcMappingContext() {
        return new R2dbcMappingContext();
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }
}
