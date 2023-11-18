package ru.clevertec.dao.aop;

import org.aspectj.lang.annotation.Pointcut;
import ru.clevertec.dao.impl.PersonRepositoryImpl;

/**
 * Точки среза для работы АОП с репозиторием {@link PersonRepositoryImpl}
 */
public class PersonAspectPointcut {

    @Pointcut("execution(* ru.clevertec.dao.PersonRepository.findById(..))")
    protected static void findByIdMethodPointcut() {
    }

    @Pointcut("execution(* ru.clevertec.dao.PersonRepository.create(..))")
    protected static void createMethodPointcut() {
    }

    @Pointcut("execution(* ru.clevertec.dao.PersonRepository.update(..))")
    protected static void updateMethodPointcut() {
    }

    @Pointcut("execution(* ru.clevertec.dao.PersonRepository.deleteById(..))")
    protected static void deleteByIdMethodPointcut() {
    }
}
