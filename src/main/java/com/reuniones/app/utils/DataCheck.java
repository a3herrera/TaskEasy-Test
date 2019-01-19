package com.reuniones.app.utils;

import java.util.List;

public class DataCheck {

    /**
     * <p>
     * This constructor method is private for avoid the class initialization
     * </p>
     */
    private DataCheck() {
        super();
    }

    /**
     * /** This method check if a {@link List} is null or not contain any value
     * @return true if is null or empty
     */
    public static <E> boolean isEmptyList(final List<E> list) {
        return (list == null || list.isEmpty());
    }
}
