package com.example.demo.util;

import lombok.experimental.UtilityClass;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class DBUtil {

    private DataSource dataSource;

    public void init(DataSource dataSource) {
        DBUtil.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
