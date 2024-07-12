package com.tutorhelper.exception;

public class AssociationAlreadyExistsException extends RuntimeException {

    public static final String ASSOCIATION_ALREADY_EXISTS_MESSAGE
        = "Association already exists between %s (ID: %d) and %s (ID: %d)";

    public AssociationAlreadyExistsException(String entityType1, Long entityId1, String entityType2, Long entityId2) {
        super(String.format(ASSOCIATION_ALREADY_EXISTS_MESSAGE, entityType1, entityId1, entityType2, entityId2));
    }
}
