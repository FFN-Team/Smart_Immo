package com.gangdestrois.smartimmo.infrastructure.rest.error;

public interface Exception {
    ExceptionEnum getError();

    String getMessage();

    void printStackTrace();
}
