package com.techcloud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central place to get a JDBC Connection to the PostgreSQL "hospital_db" database.
 *
 * Update DB_URL / DB_USER / DB_PASSWORD to match your local PostgreSQL setup.
 */
public class DBConnection {

    private static final String DB_URL      = "jdbc:postgresql://localhost:5432/hospital_db";
    private static final String DB_USER     = "postgres";
    private static final String DB_PASSWORD = "9022";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found on classpath.", e);
        }
    }

    private DBConnection() {
        // utility class - prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
