package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        var config = new Config("./data/app.properties");
        config.load();
        var driver = config.value("hibernate.connection.driver_class");
        var url = config.value("hibernate.connection.url");
        var login = config.value("hibernate.connection.username");
        var password = config.value("hibernate.connection.password");
        Class.forName(driver);
        try (var connection = DriverManager.getConnection(url, login, password)) {
            var metaDate = connection.getMetaData();
            System.out.println(metaDate.getURL());
            System.out.println(metaDate.getUserName());
        }
    }
}
