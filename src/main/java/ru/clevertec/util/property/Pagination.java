package ru.clevertec.util.property;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель для данных конфигурационного yaml-файла
 */
@Getter
@Setter
public class Pagination {

    private int pageSize;
    private int pageNumber;
}
