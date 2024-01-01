package com.gangdestrois.smartimmo.domain.prospect;

public enum ContactOrigin {
    EMAIL("Email"),
    PHONE("Phone"),
    SOCIAL_MEDIA("Social Media"),
    WEB_SITE("Web Site"),
    WORD_OF_MOUTH("Word of Mouth");

    private final String label;

    ContactOrigin(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

