package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    private void execute(String sql) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void execute(String sql, String tableName) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void createTable(String tableName) throws Exception {
        var sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();", tableName);
        execute(sql, tableName);
    }

    public void dropTable(String tableName) throws Exception {
        var sql = String.format(
                "DROP TABLE %s;", tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        var sql = String.format(
                "ALTER TABLE %s ADD %s %s;",
                tableName, columnName, type);
        execute(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        var sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        var sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        execute(sql, tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (var in = TableEditor.class.getClassLoader().getResourceAsStream("editor.properties")) {
            config.load(in);
        }
        var editor = new TableEditor(config);
        editor.createTable("students");
        editor.addColumn("students", "first_name", "VARCHAR(30)");
        editor.renameColumn("students", "first_name", "name");
        editor.dropColumn("students", "name");
        editor.dropTable("students");
    }
}
