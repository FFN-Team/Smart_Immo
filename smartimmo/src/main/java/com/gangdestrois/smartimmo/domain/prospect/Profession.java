package com.gangdestrois.smartimmo.domain.prospect;

public enum Profession {
    DOCTOR("Doctor"),
    ENGINEER("Engineer"),
    TEACHER("Teacher"),
    STUDENT("Student"),
    COMMERCIAL("Commercial");

    private final String label;

    Profession(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

