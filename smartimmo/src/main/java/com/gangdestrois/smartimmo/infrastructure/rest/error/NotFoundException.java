package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class NotFoundException extends RuntimeException {
    private final Long id;
    private final String resource;

    public NotFoundException(Long id, String resource) {
        this.id = id;
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }
}
