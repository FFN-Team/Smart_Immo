package com.gangdestrois.smartimmo.domain.prospect;

public enum AgeCategory {
    CHILD(0, 20),
    YOUNG_ADULT(21, 40),
    ADULT(41, 60),
    SENIOR(61, 80),
    ELDER(81, 120);

    int ageMin;
    int ageMax;

    AgeCategory(int ageMin, int ageMax) {
    }
}
