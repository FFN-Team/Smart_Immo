package com.gangdestrois.smartimmo.infrastructure.rest.error;
public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException(String message) {
        super(message);
    }
}