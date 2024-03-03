package com.gangdestrois.smartimmo.domain.prospect;

public enum Title {
    MR("Mr."),
    MRS("Mrs.");

    private final String label;

    Title(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
