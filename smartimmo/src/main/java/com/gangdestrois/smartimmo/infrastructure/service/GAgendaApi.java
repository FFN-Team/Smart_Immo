package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;
import com.gangdestrois.smartimmo.domain.agenda.port.VisitSaver;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.gangdestrois.smartimmo.infrastructure.service.ApplicationData.TECHNIMMO;

@Component
public class GAgendaApi implements VisitSaver {
    @Autowired
    public GAgendaApi(){
    }

    @Override
    public void saveVisit(Visit visit) throws IOException {
        Calendar service = initialize();

        Event event = new Event()
                .setSummary(visit.getVisitType() +" avec " + visit.getProspect())
                .setLocation(visit.getAdresse())
                .setDescription(visit.getComments());

        EventDateTime start = new EventDateTime()
                .setDateTime(visit.getStartDateTime());
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(visit.getEndDateTime());
        event.setEnd(end);

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("niraiksannr@gmail.com"),
                new EventAttendee().setEmail("plantefloni@gmail.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    public Calendar initialize(){
        NetHttpTransport httpTransport = new NetHttpTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        try {
            return new Calendar.Builder(httpTransport, jsonFactory,
                    GoogleApi.getCredentials(httpTransport))
                    .setApplicationName(TECHNIMMO)
                    .build();
        } catch (IOException e) {
            throw new InternalServerErrorException(ExceptionEnum.GOOGLE_CREDENTIALS_ERROR,
                    "error during google api connexion for calendar service.");
        }
    }
}
