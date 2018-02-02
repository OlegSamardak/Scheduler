package com.interlink.schedule.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.Calendar;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.service.CalendarService;
import com.interlink.calendar.service.CalendarServiceModel;
import com.interlink.calendar.service.EventService;
import com.interlink.entity.Schedule;
import com.interlink.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    private final CalendarServiceModel calendarService;

    private final EventService eventService;

    @Autowired
    public ScheduleService(CalendarServiceModel calendarService, EventService eventService, ScheduleRepository repository) {
        this.calendarService = calendarService;
        this.eventService = eventService;
        this.repository = repository;
    }

    public void addEvents(DayDto dayDto, String calendarId, Credential credential)
            throws IOException {
        List<LessonDto> lessons = dayDto.getLessons();

        for (LessonDto lesson : lessons) {
            CalendarService.createService(credential)
                    .events().insert(
                    calendarId,
                    eventService.createLessonEvent(
                            credential,
                            lesson
                    )
            ).execute();
        }
    }

    public String getCalendarId(DayDto dayDto, Credential credential)
            throws IOException {

        Calendar calendar = calendarService.createCalendar(
                credential,
                dayDto.getGroupName()
        )
                .setDescription("Schedule for " + dayDto.getGroupName());

        return CalendarService.createService(credential)
                .calendars()
                .insert(calendar)
                .execute()
                .getId();
    }

    public void saveSchedule(Schedule schedule){
        repository.save(schedule);
    }
}