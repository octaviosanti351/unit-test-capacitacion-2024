package com.testing.course;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Configuration
@Testcontainers
public class TestDatabaseConfig{


    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withDatabaseName("integration-tests-db")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("dummy-data.sql");

    static {
        postgres.start();
    }

    @Bean
    @Primary
    public HikariDataSource dataSource(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setUsername(postgres.getUsername());
        hikariConfig.setPassword(postgres.getPassword());
        hikariConfig.setDriverClassName(postgres.getDriverClassName());
        hikariConfig.setJdbcUrl(postgres.getJdbcUrl());
        HikariDataSource hikariDataSource=new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

}