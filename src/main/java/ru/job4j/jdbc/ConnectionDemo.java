package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

public class ConnectionDemo {

    private static Connection getConnection() throws SQLException {
        Config config = new Config("app.properties");
        config.load();
        config.value("jdbc.driver");
        return DriverManager.getConnection(config.value("jdbc.url"),
                config.value("jdbc.login"),
                config.value("jdbc.password"));
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists demo_table(%s, %s);",
                    "id serial primary key",
                    "name varchar(255)");
            statement.execute(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        }
    }
}
