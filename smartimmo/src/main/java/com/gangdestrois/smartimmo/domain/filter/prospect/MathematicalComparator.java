package com.gangdestrois.smartimmo.domain.filter.prospect;

public enum MathematicalComparator {
    EQUALS("="),
    NOT_EQUAL_TO("<>"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    LESS_THAN_OR_EQUAL_TO("<=")
    ;

    String mathematical_sign;
    MathematicalComparator(String mathematical_sign) {
        this.mathematical_sign=mathematical_sign;
    }
}
