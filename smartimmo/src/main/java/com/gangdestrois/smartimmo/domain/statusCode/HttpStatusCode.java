package com.gangdestrois.smartimmo.domain.statusCode;

public enum HttpStatusCode {
    FORBIDDEN(403, "Forbidden"),
    UNAUTHORIZED(401, "Unauthorized");

    private final int code;
    private final String message;

    HttpStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
