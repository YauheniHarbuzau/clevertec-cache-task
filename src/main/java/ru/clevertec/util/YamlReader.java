package ru.clevertec.util;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.clevertec.util.property.Property;

/**
 * Класс для чтения конфигурационного yaml-файла
 */
@Component
public class YamlReader {

    public Property getProperties() {
        var inputStream = Property.class.getClassLoader().getResourceAsStream("application.yaml");
        var yaml = new Yaml(new Constructor(Property.class, new LoaderOptions()));
        return yaml.load(inputStream);
    }
}
