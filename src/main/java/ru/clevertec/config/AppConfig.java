package ru.clevertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.clevertec.controller.PersonController;
import ru.clevertec.dao.PersonRepository;
import ru.clevertec.dao.cache.CacheFactory;
import ru.clevertec.dao.cache.impl.CacheFactoryImpl;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.dao.impl.PersonRepositoryImpl;
import ru.clevertec.dao.proxy.PersonRepositoryProxy;
import ru.clevertec.service.PersonService;
import ru.clevertec.service.impl.PersonServiceImpl;
import ru.clevertec.service.mapper.PersonMapper;
import ru.clevertec.service.mapper.PersonMapperImpl;
import ru.clevertec.service.proxy.PersonServiceProxy;
import ru.clevertec.util.YamlReader;

/**
 * Класс для конфигурации Spring-приложения
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.clevertec")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public YamlReader yamlReader() {
        return new YamlReader();
    }

    @Bean
    public ConnectionConfig connectionConfig() {
        return new ConnectionConfig(yamlReader());
    }

    @Bean
    public CacheFactory<Long, Person> cacheFactoryImpl() {
        return new CacheFactoryImpl<>(yamlReader());
    }

    @Bean
    public PersonRepository personRepositoryImpl() {
        return new PersonRepositoryImpl(connectionConfig());
    }

    @Bean
    public PersonRepository personRepositoryProxy() {
        return new PersonRepositoryProxy(personRepositoryImpl(), cacheFactoryImpl());
    }

    @Bean
    public PersonMapper personMapperImpl() {
        return new PersonMapperImpl();
    }

    @Bean
    public PersonService personServiceImpl() {
        return new PersonServiceImpl(personRepositoryProxy(), personMapperImpl(), yamlReader());
    }

    @Bean
    public PersonService personServiceProxy() {
        return new PersonServiceProxy(personServiceImpl());
    }

    @Bean
    public PersonController personController() {
        return new PersonController(personServiceProxy());
    }
}
