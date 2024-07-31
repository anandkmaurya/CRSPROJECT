package com.gniot.crs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private static final String PROPERTIES_FILE = "./config.properties";

    public static Connection getConnection() {
        Connection connection = null;
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            props.load(fis);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

	public static void closeResources(PreparedStatement statement, Connection connection) {
		// TODO Auto-generated method stub
		
	}
}
