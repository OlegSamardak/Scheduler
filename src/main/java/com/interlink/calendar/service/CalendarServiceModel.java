package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalendarServiceModel {

    public com.google.api.services.calendar.model.Calendar createCalendar
            (Credential credential, String groupName)
            throws IOException {
        com.google.api.services.calendar.model.Calendar calendar
                = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(groupName);
        calendar.setSummary("Schedule for " + groupName + " .");

        return CalendarService.createService(credential).calendars().insert(calendar).execute();
    }
}