package com.interlink.schedule.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.service.CalendarService;
import com.interlink.calendar.service.EventService;
import com.interlink.entity.Schedule;
import com.interlink.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.TimeZone;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    private final EventService eventService;

    @Autowired
    public ScheduleService(EventService eventService, ScheduleRepository repository) {
        this.eventService = eventService;
        this.repository = repository;
    }

    public void addEvents(DayDto dayDto, String calendarId, Credential credential)
            throws IOException {
        List<LessonDto> lessons = dayDto.getLessons();
        com.google.api.services.calendar.Calendar calendarService
                = CalendarService.createService(credential);
        for (LessonDto lesson : lessons) {
            calendarService
                    .events().insert(
                    calendarId,
                    eventService.createLessonEvent(
                            lesson
                    )
            ).execute();
        }
    }

    public String getCalendarId(DayDto dayDto, Credential credential)
            throws IOException {
        com.google.api.services.calendar.Calendar calendarService
                = CalendarService.createService(credential);
        com.google.api.services.calendar.model.Calendar calendar
                = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary("Schedule for " + dayDto.getGroupName());
        calendar.setTimeZone(TimeZone.getDefault().getID());

        return calendarService.calendars().insert(calendar).execute().getId();
    }

    public void saveSchedule(Schedule schedule) {
        repository.save(schedule);
    }
}