package com.tutorhelper.exception;

public class AssociationNotFoundException extends RuntimeException {

    public static final String ASSOCIATION_NOT_FOUND_MESSAGE
        = "Association not found between %s (ID: %d) and %s (ID: %d)";

    public AssociationNotFoundException(String entityType1, Long entityId1, String entityType2, Long entityId2) {
        super(String.format(ASSOCIATION_NOT_FOUND_MESSAGE, entityType1, entityId1, entityType2, entityId2));
    }
}
