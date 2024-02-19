package com.gangdestrois.smartimmo.domain.error;

public interface Exception {
    ExceptionEnum getError();

    String getMessage();

    void printStackTrace();
}
