package ru.clevertec.dao.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import ru.clevertec.dao.cache.Cache;
import ru.clevertec.dao.cache.CacheFactory;
import ru.clevertec.dao.cache.impl.CacheFactoryImpl;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.dao.impl.PersonRepositoryImpl;
import ru.clevertec.util.XmlUtil;

import java.util.Optional;

/**
 * Реализация АОП для кеширования данных при работе с {@link PersonRepositoryImpl}
 */
@Aspect
public class PersonAspect {

    private final CacheFactory<Long, Person> cacheFactory;
    private final Cache<Long, Person> cache;

    public PersonAspect() {
        this.cacheFactory = new CacheFactoryImpl<>();
        this.cache = cacheFactory.initCache();
    }

    @Around("ru.clevertec.dao.aop.PersonAspectPointcut.findByIdMethodPointcut()")
    public Optional<Person> aroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        var id = (Long) joinPoint.getArgs()[0];

        Optional<Person> person;
        if (cache.get(id).isPresent()) {
            person = cache.get(id);
        } else {
            person = (Optional<Person>) joinPoint.proceed();
            person.ifPresent(p -> cache.put(p.getId(), p));
        }

        XmlUtil.writeXml(person.get(), "findById");
        return person;
    }

    @Around("PersonAspectPointcut.createMethodPointcut()")
    public Person aroundCreate(ProceedingJoinPoint joinPoint) throws Throwable {
        var person = (Person) joinPoint.proceed();
        cache.put(person.getId(), person);
        return person;
    }

    @Around("PersonAspectPointcut.updateMethodPointcut()")
    public Person aroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        var person = (Person) joinPoint.proceed();
        cache.put(person.getId(), person);
        return person;
    }

    @Around("PersonAspectPointcut.deleteByIdMethodPointcut()")
    public Object aroundDeleteById(ProceedingJoinPoint joinPoint) throws Throwable {
        var id = (Long) joinPoint.getArgs()[0];
        if (cache.get(id).isPresent()) {
            cache.remove(id);
        }
        return joinPoint.proceed();
    }
}
