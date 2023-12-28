package com.gangdestrois.smartimmo.domain.email;

public interface EmailSender {
    void sendEmail(String subject, String message, String senderEmail, String recipientEmail) throws Exception;
}
