package com.gangdestrois.smartimmo.domain.event;

public enum EventType {
    PROJECT_DUE_DATE_APPROACHING("Suivi de votre projet immobilier imminent",
            "notification_potential_project.html"),
    PROSPECT_MAY_BUY_BIGGER_HOUSE("Découvrez des opportunités passionnantes pour une nouvelle maison",
            "notification_prospect_may_buy_bigger_house.html");

    private final String emailSubject;
    private final String emailTemplate;

    EventType(String emailSubject, String emailTemplate) {
        this.emailSubject = emailSubject;
        this.emailTemplate = emailTemplate;
    }

    public String emailSubject() {
        return this.emailSubject;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }
}
