package com.example.demo.config;

import com.example.demo.util.DBUtil;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    public DBConfig(DataSource dataSource) {
        DBUtil.init(dataSource);
    }
}