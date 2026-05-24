package com.f1fitness.backend;

import com.f1fitness.backend.config.DatabaseCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class F1FitnessBackendApplication {

    public static void main(String[] args) {
        // Automatically check and create database if missing before JPA initializes
        DatabaseCreator.createDatabaseIfNotExist();
        
        SpringApplication.run(F1FitnessBackendApplication.class, args);
    }
}
