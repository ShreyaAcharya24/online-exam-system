package com.roima.exam.online_exam_system;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseConnectionCheker {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Database connection successful: " + connection.getMetaData().getURL());
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}

