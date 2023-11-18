package ru.clevertec.util.property;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель для данных конфигурационного yaml-файла
 */
@Getter
@Setter
public class DataSource {

    private String driver;
    private String url;
    private String username;
    private String password;
}
