package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.login"),
                    properties.getProperty("jdbc.password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s();", tableName);
        executeQuery(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s", tableName);
        executeQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add column %s %s", tableName, columnName, type);
        executeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        executeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        executeQuery(sql);
    }

    private void executeQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s | %-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from %s limit 1", tableName));
            var data = selection.getMetaData();
            for (int i = 1; i < data.getColumnCount(); i++) {
                buffer.add(String.format("%-15s | %-15s%n", data.getColumnName(i), data.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            String tableName = "test_database";
            String columnName = "next_column";
            String columnType = "varchar(255)";
            String newColumnName = "new_column";
            tableEditor.createTable(tableName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.addColumn(tableName, columnName, columnType);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.renameColumn(tableName, columnName, newColumnName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropColumn(tableName, newColumnName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropTable(tableName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
        }
    }
}
