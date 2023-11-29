package ru.clevertec.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

import static ru.clevertec.constant.Constant.ZONE_OFFSET;

/**
 * Утилитарный класс для преобразования Timestamp в OffsetDateTime
 */
@UtilityClass
public class DateUtil {

    public OffsetDateTime getOffsetDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().atOffset(ZONE_OFFSET);
    }
}
