package com.interlink.calendar.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.collect.Iterables;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.*;
import java.util.*;

public class DateService {

    public EventDateTime getEventDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        EventDateTime eventDateTime = new EventDateTime();
        DateTime dateTime = new DateTime(Date.from(localDateTime.atZone(zoneId).toInstant()));

        return eventDateTime.setDate(dateTime);
    }

    public Map<LocalDateTime, LocalDateTime> getLessonInterim
            (LocalDate day, LocalTime firstLesson,
             List<Integer> breaks, int lessonMinutesDuration) {
        Map<LocalDateTime, LocalDateTime> lessonsInterim = new HashMap<>();

        for (int i = 0; i <= 8; i++) {
            LocalDateTime lessonStart;
            LocalDateTime lessonEnd;
            if (i == 0) {
                lessonStart = LocalDateTime.of(day, firstLesson);
                lessonEnd = lessonStart.plusMinutes(breaks.get(i));
                lessonsInterim.put(lessonStart, lessonEnd);
            } else {
                List<LocalDateTime> ends = new ArrayList<>(lessonsInterim.values());

                lessonStart = LocalDateTime.of(
                        day,
                        ends.get(i - 1)
                                .toLocalTime()
                                .plusMinutes(breaks.get(i - 1)));
                lessonEnd = lessonStart.plusMinutes(breaks.get(i));
                lessonsInterim.put(lessonStart, lessonEnd);
            }
        }

        return lessonsInterim;
    }
}