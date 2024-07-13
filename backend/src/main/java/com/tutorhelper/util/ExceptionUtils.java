package com.tutorhelper.util;

import java.util.function.Supplier;

import com.tutorhelper.exception.AssociationAlreadyExistsException;
import com.tutorhelper.exception.AssociationNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;

/**
 * Utility class for creating and throwing entity-related exceptions.
 */
public final class ExceptionUtils {

    /**
     * Creates an EntityNotFoundException for a given entity type and ID.
     *
     * @param <T> The type of the entity
     * @param entityType The class of the entity
     * @param entityId The ID of the entity
     * @return An EntityNotFoundException with a formatted message
     */
    public static <T> EntityNotFoundException entityNotFoundException(
        @NonNull Class<T> entityType, @NonNull Long entityId
    ) {
        String entityName = entityType.getSimpleName();
        return new EntityNotFoundException(String.format("%s with ID %d not found", entityName, entityId));
    }

    /**
     * Creates a Supplier of EntityNotFoundException for a given entity type and ID.
     * Useful for methods like Optional.orElseThrow().
     *
     * @param <T> The type of the entity
     * @param entityType The class of the entity
     * @param entityId The ID of the entity
     * @return A Supplier of EntityNotFoundException
     */
    public static <T> Supplier<EntityNotFoundException> entityNotFoundExceptionSupplier(
        @NonNull Class<T> entityType, @NonNull Long entityId
    ) {
        return () -> entityNotFoundException(entityType, entityId);
    }

    /**
     * Throws an EntityNotFoundException for a given entity type and ID.
     *
     * @param <T> The type of the entity
     * @param entityType The class of the entity
     * @param entityId The ID of the entity
     * @throws EntityNotFoundException always
     */
    public static <T> void throwEntityNotFoundException(
        @NonNull Class<T> entityType, @NonNull Long entityId
    ) {
        throw entityNotFoundException(entityType, entityId);
    }

    /**
     * Creates an AssociationAlreadyExistsException for two given entity types and their IDs.
     *
     * @param <T> The type of the entities
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @return An AssociationAlreadyExistsException with a formatted message
     */
    public static <T, U> AssociationAlreadyExistsException associationAlreadyExistsException(
        @NonNull Class<T> firstEntityType, Long firstEntityId, @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        String firstEntityName = firstEntityType.getSimpleName();
        String secondEntityName = secondEntityType.getSimpleName();
        return new AssociationAlreadyExistsException(firstEntityName, firstEntityId, secondEntityName, secondEntityId);
    }

    /**
     * Creates a Supplier of AssociationAlreadyExistsException for two given entity types and their IDs.
     * Useful for methods like Optional.orElseThrow().
     *
     * @param <T> The type of the entities
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @return A Supplier of AssociationAlreadyExistsException
     */
    public static <T, U> Supplier<AssociationAlreadyExistsException> associationAlreadyExistsExceptionSupplier(
        @NonNull Class<T> firstEntityType, Long firstEntityId, @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        return () -> associationAlreadyExistsException(
            firstEntityType,
            firstEntityId,
            secondEntityType,
            secondEntityId
        );
    }

    /**
     * Throws an AssociationAlreadyExistsException for two given entity types and their IDs.
     *
     * @param <T> The type of the entities
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @throws AssociationAlreadyExistsException always
     */
    public static <T, U> void throwAssociationAlreadyExistsException(
        @NonNull Class<T> firstEntityType, Long firstEntityId, @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        String firstEntityName = firstEntityType.getSimpleName();
        String secondEntityName = secondEntityType.getSimpleName();
        throw new AssociationAlreadyExistsException(firstEntityName, firstEntityId, secondEntityName, secondEntityId);
    }

    /**
     * Creates an AssociationNotFoundException for two given entity types and their IDs.
     *
     * @param <T> The type of the first entity
     * @param <U> The type of the second entity
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @return An AssociationNotFoundException with a formatted message
     */
    public static <T, U> AssociationNotFoundException associationNotFoundException(
        @NonNull Class<T> firstEntityType, Long firstEntityId,
        @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        String firstEntityName = firstEntityType.getSimpleName();
        String secondEntityName = secondEntityType.getSimpleName();
        return new AssociationNotFoundException(firstEntityName, firstEntityId, secondEntityName, secondEntityId);
    }

    /**
     * Creates a Supplier of AssociationNotFoundException for two given entity types and their IDs.
     * Useful for methods like Optional.orElseThrow().
     *
     * @param <T> The type of the first entity
     * @param <U> The type of the second entity
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @return A Supplier of AssociationNotFoundException
     */
    public static <T, U> Supplier<AssociationNotFoundException> associationNotFoundExceptionSupplier(
        @NonNull Class<T> firstEntityType, Long firstEntityId,
        @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        return () -> associationNotFoundException(firstEntityType, firstEntityId, secondEntityType, secondEntityId);
    }

    /**
     * Throws an AssociationNotFoundException for two given entity types and their IDs.
     *
     * @param <T> The type of the first entity
     * @param <U> The type of the second entity
     * @param firstEntityType The class of the first entity
     * @param firstEntityId The ID of the first entity
     * @param secondEntityType The class of the second entity
     * @param secondEntityId The ID of the second entity
     * @throws AssociationNotFoundException always
     */
    public static <T, U> void throwAssociationNotFoundException(
        @NonNull Class<T> firstEntityType, Long firstEntityId,
        @NonNull Class<U> secondEntityType, Long secondEntityId
    ) {
        throw associationNotFoundException(firstEntityType, firstEntityId, secondEntityType, secondEntityId);
    }
}
