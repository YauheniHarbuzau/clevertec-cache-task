package ru.clevertec.dao.cache.impl;

import ru.clevertec.dao.cache.Cache;
import ru.clevertec.dao.cache.CacheFactory;
import ru.clevertec.util.YamlUtil;

/**
 * Имплементация фабрики для инициализации алгоритма кеширования данных
 */
public class CacheFactoryImpl<K, V> implements CacheFactory<K, V> {

    private final String cacheType = YamlUtil.getProperties().getCache().getType();
    private final Integer cacheCapacity = YamlUtil.getProperties().getCache().getCapacity();

    @Override
    public Cache<K, V> initCache() {
        return switch (cacheType) {
            case "LRU" -> new LRUCache<>(cacheCapacity);
            case "LFU" -> new LFUCache<>(cacheCapacity);
            default -> throw new IllegalArgumentException("Only LRU or LFU cache algorithms");
        };
    }
}
