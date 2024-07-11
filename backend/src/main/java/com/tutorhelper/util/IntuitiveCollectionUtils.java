package com.tutorhelper.util;

import java.util.Collection;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

/**
 * Provides intuitive utility methods for working with Collections.
 */
@UtilityClass
public class IntuitiveCollectionUtils {

    /**
     * Checks if the given collection is not null and contains at least one element.
     *
     * @param collection the collection to check, may be null
     * @return true if the collection is not null and not empty, false otherwise
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }
}
