package com.interlink.schedule.service;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.dto.TemplateDto;
import com.interlink.calendar.dto.WeekDto;
import com.interlink.calendar.enums.WeekType;
import com.interlink.calendar.service.CalendarService;
import com.interlink.calendar.service.EventService;
import com.interlink.entity.Schedule;
import com.interlink.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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

    public void addEvents(TemplateDto templateDto, String calendarId, Credential credential)
            throws IOException {
        List<WeekDto> weeks = templateDto.getWeeks();
        com.google.api.services.calendar.Calendar calendarService
                = CalendarService.createService(credential);
        for (WeekDto week : weeks) {
            for (DayDto day : week.getDays()) {
                for (LessonDto lesson : day.getLessons()) {
                    if (!Objects.equals(lesson.getTitle(), "none")) {
                        calendarService
                                .events().insert(
                                calendarId,
                                eventService.createLessonEvent(
                                        lesson,
                                        getReccurenceTimeByWeek(templateDto, week)
                                )
                        ).execute();
                    }
                }
            }
        }
    }

    private int getReccurenceTimeByWeek(TemplateDto templateDto, WeekDto weekDto) {
        int reccurenceTime;
        if (templateDto.getWeeksCount() % 2 == 0) {
            reccurenceTime = templateDto.getWeeksCount() / 2;
        } else {
            if (weekDto.getWeekType().equals(WeekType.UPPER)) {
                reccurenceTime = templateDto.getWeeksCount() / 2;
            } else {
                reccurenceTime = templateDto.getWeeksCount() / 2 + 1;
            }
        }

        return reccurenceTime;
    }

    public String getCalendarId(TemplateDto templateDto, Credential credential)
            throws IOException {
        com.google.api.services.calendar.Calendar calendarService
                = CalendarService.createService(credential);
        com.google.api.services.calendar.model.Calendar calendar
                = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary("Розклад для " + templateDto.getGroupName());
        calendar.setTimeZone(TimeZone.getDefault().getID());

        return calendarService.calendars().insert(calendar).execute().getId();
    }

    public void saveSchedule(Schedule schedule) {
        repository.save(schedule);
    }
}