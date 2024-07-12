package com.tutorhelper.utils;

import jakarta.persistence.EntityNotFoundException;
import java.util.function.Supplier;
import lombok.NonNull;

/**
 * Utility class for handling entity-related exceptions.
 */
public final class ExceptionUtils {

    /**
     * Creates an EntityNotFoundException for a given entity class and id.
     *
     * @param <T> The type of the entity
     * @param entityClass The class of the entity
     * @param id The id of the entity
     * @return An EntityNotFoundException with a formatted message
     */
    public static <T> EntityNotFoundException entityNotFoundException(
        @NonNull Class<T> entityClass,
        @NonNull Long id
    ) {
        String entityName = entityClass.getSimpleName();
        return new EntityNotFoundException(
            String.format("%s with id %d not found", entityName, id)
        );
    }

    /**
     * Creates a Supplier of EntityNotFoundException for a given entity class and id.
     * <p>
     * This is useful for methods like Optional.orElseThrow()
     *
     * @param <T> The type of the entity
     * @param entityClass The class of the entity
     * @param id The id of the entity
     * @return A Supplier of EntityNotFoundException
     */
    public static <T> Supplier<EntityNotFoundException> entityNotFoundExceptionSupplier(
        @NonNull Class<T> entityClass,
        @NonNull Long id
    ) {
        return () -> entityNotFoundException(entityClass, id);
    }

    /**
     * Throws an EntityNotFoundException for a given entity class and id.
     *
     * @param <T> The type of the entity
     * @param entityClass The class of the entity
     * @param id The id of the entity
     * @throws EntityNotFoundException always
     */
    public static <T> void throwEntityNotFoundException(
        @NonNull Class<T> entityClass,
        @NonNull Long id
    ) {
        throw entityNotFoundException(entityClass, id);
    }
}
