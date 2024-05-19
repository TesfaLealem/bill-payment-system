package com.example.billpaymentsystemdemo.exceptions;

import com.example.billpaymentsystemdemo.models.BaseEntity;

import java.util.Optional;

public class ResourceNotFoundException extends RuntimeException {
    private Class<? extends BaseEntity> resource;

    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Class<? extends BaseEntity> resource) {
        super(
                Optional.ofNullable(resource).map(Class::getSimpleName).orElse("Resource") + " not found");
        this.resource = resource;
    }

    public Class<? extends BaseEntity> getResourceType() {
        return resource;
    }
}
