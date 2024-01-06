package com.gangdestrois.smartimmo.infrastructure.rest.error;

public interface Exception {
    String getError();
    String getMessage();
    String getDetails();
    void printStackTrace();
}
