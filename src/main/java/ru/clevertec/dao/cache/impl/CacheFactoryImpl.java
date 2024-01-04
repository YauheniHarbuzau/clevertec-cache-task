package ru.clevertec.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.dao.cache.Cache;
import ru.clevertec.dao.cache.CacheFactory;
import ru.clevertec.util.YamlReader;

/**
 * Имплементация фабрики для инициализации алгоритма кеширования данных
 */
@Component
public class CacheFactoryImpl<K, V> implements CacheFactory<K, V> {

    private final YamlReader yamlReader;

    @Autowired
    public CacheFactoryImpl(YamlReader yamlReader) {
        this.yamlReader = yamlReader;
    }

    @Override
    public Cache<K, V> initCache() {
        var cacheType = yamlReader.getProperties().getCache().getType();
        var cacheCapacity = yamlReader.getProperties().getCache().getCapacity();

        return switch (cacheType) {
            case "LRU" -> new LRUCache<>(cacheCapacity);
            case "LFU" -> new LFUCache<>(cacheCapacity);
            default -> throw new IllegalArgumentException("Only LRU or LFU cache algorithms");
        };
    }
}
