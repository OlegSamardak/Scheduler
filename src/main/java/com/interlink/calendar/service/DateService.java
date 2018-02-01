package com.interlink.calendar.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.exceptions.InvalidCountOfBreaks;

import java.sql.Date;
import java.time.*;
import java.util.*;

public class DateService {

    private static final int LESSONS_COUNT = 8;

    public static EventDateTime getEventDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        EventDateTime eventDateTime = new EventDateTime();
        DateTime dateTime = new DateTime(Date.from(localDateTime.atZone(zoneId).toInstant()));

        return eventDateTime.setDate(dateTime);
    }

    public Map<LocalDateTime, LocalDateTime> getLessonInterim
            (LocalDateTime firstLesson, List<Integer> breaks, int lessonMinutesDuration)
            throws InvalidCountOfBreaks {
        if (breaks.size() >= LESSONS_COUNT) {
            throw new InvalidCountOfBreaks(
                    "Count of breaks cant be equal or larger than count of lessons. " +
                            "Breaks : " + breaks.size() + "Lessons : " + LESSONS_COUNT);
        }

        Map<LocalDateTime, LocalDateTime> lessonsInterim = new TreeMap<>();
        List<LocalDateTime> lessonStarts = new ArrayList<>();
        List<LocalDateTime> lessonEnds = new ArrayList<>();

        for (int i = 0; i <= LESSONS_COUNT - 1; i++) {
            LocalDateTime lessonStart;
            LocalDateTime lessonEnd;
            if (i == 0) {
                lessonStart = firstLesson;
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
            } else {
                lessonStart = lessonEnds.get(i - 1).plusMinutes(breaks.get(i - 1));
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
            }
            lessonStarts.add(lessonStart);
            lessonEnds.add(lessonEnd);
            lessonsInterim.put(lessonStart, lessonEnd);
        }

        return lessonsInterim;
    }

    public Map<EventDateTime, EventDateTime> getEventDateTimes
            (Map<LocalDateTime, LocalDateTime> lessonsInterim) {
        Map<EventDateTime, EventDateTime> eventsInterim = new TreeMap<>();

        for (LocalDateTime startTime : lessonsInterim.keySet()) {
            eventsInterim.put(
                    getEventDateTime(startTime),
                    getEventDateTime(lessonsInterim.get(startTime)));
        }

        return eventsInterim;
    }
}