package ru.clevertec.dao.proxy;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.clevertec.dao.PersonRepository;
import ru.clevertec.dao.cache.Cache;
import ru.clevertec.dao.cache.CacheFactory;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.dao.impl.PersonRepositoryImpl;
import ru.clevertec.util.XmlUtil;

import java.util.List;
import java.util.Optional;

/**
 * Proxy для {@link PersonRepository} и {@link PersonRepositoryImpl}
 */
@Component
public class PersonRepositoryProxy implements PersonRepository {

    private final PersonRepository repository;
    private final Cache<Long, Person> cache;

    @Autowired
    public PersonRepositoryProxy(@Qualifier("personRepositoryImpl") PersonRepository repository, CacheFactory<Long, Person> cacheFactory) {
        this.repository = repository;
        this.cache = cacheFactory.initCache();
    }

    @SneakyThrows
    @Override
    public Optional<Person> findById(Long id) {
        Optional<Person> person;

        if (cache.get(id).isPresent()) {
            person = cache.get(id);
        } else {
            person = repository.findById(id);
        }

        XmlUtil.writeXml(person.get(), "findById");
        return person;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Person> findAll(int pageSize, int pageNumber) {
        return repository.findAll(pageSize, pageNumber);
    }

    @Override
    public Person create(Person person) {
        cache.put(person.getId(), person);
        return person;
    }

    @Override
    public Person update(Person person) {
        cache.put(person.getId(), person);
        return person;
    }

    @Override
    public void deleteById(Long id) {
        if (cache.get(id).isPresent()) {
            cache.remove(id);
        }
        repository.deleteById(id);
    }
}
