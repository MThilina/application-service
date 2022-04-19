package com.thilinam.applicationservice.rest.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @param <S> Server Object Type
 * @param <R> Rest Object Type
 * @implNote Generic Method to unmarshel server request and mapping to rest request
 */
public interface RestMapper<S,R> {
    R convertToRest(S serverObject);

    default List<R> convertToRest(final List<S> objects){
        return objects!=null
                ?objects.stream().map(this::convertToRest).collect(Collectors.toList())
                : Collections.emptyList();
    }

    default Set<R> convertToRest(final Set<S> objects){
        return objects!=null
                ?objects.stream().map(this::convertToRest).collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
