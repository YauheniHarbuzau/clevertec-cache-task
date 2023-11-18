package ru.clevertec.util;

import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.clevertec.util.property.Property;

/**
 * Утилитарный класс для чтения конфигурационного yaml-файла
 */
@UtilityClass
public class YamlUtil {

    public Property getProperties() {
        var inputStream = Property.class.getClassLoader().getResourceAsStream("application.yaml");
        var yaml = new Yaml(new Constructor(Property.class, new LoaderOptions()));
        return yaml.load(inputStream);
    }
}
