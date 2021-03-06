package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import com.interlink.calendar.enums.LessonType;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    public static Calendar createService(Credential credential) {
        return new Calendar(
                credential.getTransport(),
                credential.getJsonFactory(),
                credential
        );
    }
}