package com.picpayclone.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

// <T> = generic class
public abstract class ResourceBase<T> {

    protected ResponseEntity<T> responseCreatedItem(T object) {
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }

    protected ResponseEntity<T> responseCreatedItemWithURI(T object, UriComponentsBuilder uriBuilder, String path,
                                                          String code) {
        URI uri = uriBuilder.path(path).buildAndExpand(code).toUri();
        return ResponseEntity.created(uri).body(object);
    }

    protected ResponseEntity<T> responseItemNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    protected ResponseEntity<T> responseSuccess() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    protected ResponseEntity<T> responseItemSuccess(T object) {
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }

    protected ResponseEntity<List<T>> responseEmptyList() {
        List<T> emptyList = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(emptyList);
    }

    protected ResponseEntity<List<T>> responseItemsList(List<T> items) {
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    protected ResponseEntity<T> responseBadRequest() {
        return ResponseEntity.badRequest().build();
    }

    protected ResponseEntity<Page<T>> responsePaginatedItemsList(Page<T> items) {
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}
