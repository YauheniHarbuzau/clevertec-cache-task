package ru.clevertec.util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Утилитарный класс для выполнения и закрытия подключения к базе данных
 */
@UtilityClass
public class ConnectionUtil {

    public Connection open() {
        try {
            Class.forName(YamlUtil.getProperties().getDataSource().getDriver());
            return DriverManager.getConnection(
                    YamlUtil.getProperties().getDataSource().getUrl(),
                    YamlUtil.getProperties().getDataSource().getUsername(),
                    YamlUtil.getProperties().getDataSource().getPassword()
            );
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
