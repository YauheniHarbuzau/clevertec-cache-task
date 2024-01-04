package ru.clevertec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.util.YamlReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для выполнения подключения к базе данных
 */
@Component
public class ConnectionConfig {

    private final YamlReader yamlReader;

    @Autowired
    public ConnectionConfig(YamlReader yamlReader) {
        this.yamlReader = yamlReader;
    }

    public Connection getConnection() {
        try {
            Class.forName(yamlReader.getProperties().getDataSource().getDriver());
            return DriverManager.getConnection(
                    yamlReader.getProperties().getDataSource().getUrl(),
                    yamlReader.getProperties().getDataSource().getUsername(),
                    yamlReader.getProperties().getDataSource().getPassword()
            );
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
