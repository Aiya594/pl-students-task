package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    @Value("${spring.datasource.url}")
    private   String url;

    @Value("${spring.datasource.user}")
    private   String user;

    @Value("${spring.datasource.password}")
    private   String password;

    @Bean
    public Connection connect() throws SQLException{
        return DriverManager.getConnection(url,user, password);
    }
}
