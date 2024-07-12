package com.tutorhelper.utils;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LocationURIBuilder {

    public static URI buildLocationURI(String path, Object... uriVariableValues) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path(path)
            .buildAndExpand(uriVariableValues)
            .toUri();
    }

}
