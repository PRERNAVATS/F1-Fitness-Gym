package com.f1fitness.backend.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseCreator {
    public static void createDatabaseIfNotExist() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseCreator.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("DatabaseCreator: application.properties not found on classpath!");
                return;
            }
            properties.load(input);
            
            String defaultUrl = properties.getProperty("postgres.default.url", "jdbc:postgresql://localhost:5432/postgres");
            String dbName = properties.getProperty("postgres.db.name", "f1_fitness_db");
            String username = properties.getProperty("spring.datasource.username", "postgres");
            String password = properties.getProperty("spring.datasource.password", "postgres");
            String driverClassName = properties.getProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

            // Load database driver
            Class.forName(driverClassName);

            System.out.println("DatabaseCreator: Checking if database '" + dbName + "' exists...");
            try (Connection conn = DriverManager.getConnection(defaultUrl, username, password);
                 Statement stmt = conn.createStatement()) {
                
                // Run query to check if database exists
                String checkQuery = "SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'";
                boolean dbExists = false;
                try (ResultSet rs = stmt.executeQuery(checkQuery)) {
                    if (rs.next()) {
                        dbExists = true;
                    }
                }

                if (dbExists) {
                    System.out.println("DatabaseCreator: Database '" + dbName + "' already exists.");
                } else {
                    System.out.println("DatabaseCreator: Database '" + dbName + "' not found. Attempting to create it...");
                    stmt.executeUpdate("CREATE DATABASE " + dbName);
                    System.out.println("DatabaseCreator: Database '" + dbName + "' created successfully.");
                }
            }
        } catch (Exception e) {
            System.err.println("DatabaseCreator WARNING: Auto-creation check failed: " + e.getMessage());
            System.err.println("Please make sure PostgreSQL is running on port 5432 with credentials matching application.properties.");
        }
    }
}
