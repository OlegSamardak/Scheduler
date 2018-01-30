package com.interlink.calendar.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.collect.Iterables;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.*;
import java.util.*;

public class DateService {

    private static final int LESSONS_COUNT = 8;

    public EventDateTime getEventDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        EventDateTime eventDateTime = new EventDateTime();
        DateTime dateTime = new DateTime(Date.from(localDateTime.atZone(zoneId).toInstant()));

        return eventDateTime.setDate(dateTime);
    }

    public static Map<LocalDateTime, LocalDateTime> getLessonInterim
            (LocalDateTime firstLesson, int breakLesson, int lessonMinutesDuration) {
        Map<LocalDateTime, LocalDateTime> lessonsInterim = new HashMap<>();
        List<LocalDateTime> lessonStarts = new ArrayList<>();
        List<LocalDateTime> lessonEnds = new ArrayList<>();

        for (int i = 0; i <= LESSONS_COUNT - 1; i++) {
            LocalDateTime lessonStart;
            LocalDateTime lessonEnd;
            if (i == 0) {
                lessonStart = firstLesson;
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
                lessonStarts.add(lessonStart);
                lessonEnds.add(lessonEnd);
            } else {
                lessonStart = lessonEnds.get(i - 1).plusMinutes(breakLesson);
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
                lessonStarts.add(lessonStart);
                lessonEnds.add(lessonEnd);
            }
        }
        return lessonsInterim;
    }
}