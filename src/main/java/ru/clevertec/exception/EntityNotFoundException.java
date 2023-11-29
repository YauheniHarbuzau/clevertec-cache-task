package ru.clevertec.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(String.format("%s with id %s not found", entityClass.getSimpleName(), id));
    }
}
