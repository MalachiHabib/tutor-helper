package com.tutorhelper.response;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {

    private final List<T> data;
    private final int count;

    public static <T> PagedResponse<T> from(List<T> content) {
        return new PagedResponse<>(content, content.size());
    }

}
