package com.interlink.calendar.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.IterimsWrapper;
import com.interlink.calendar.exceptions.InvalidCountOfBreaks;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.util.*;

@Service
public class DateService {

    private static final int LESSONS_COUNT = 9;

    public static EventDateTime getEventDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        EventDateTime eventDateTime = new EventDateTime();

        return eventDateTime.setDateTime(
                new DateTime(Date.from(localDateTime.atZone(zoneId).toInstant()))
        );
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
        System.out.println(breaks);
        for (int i = 0; i <= LESSONS_COUNT - 1; i++) {
            LocalDateTime lessonStart;
            LocalDateTime lessonEnd;
            if (i == 0) {
                lessonStart = firstLesson;
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
            } else {
                lessonStart = lessonEnds.get(i - 1).plusMinutes(breaks.get(i - 1));
                System.out.println(lessonStart);
                System.out.println(breaks.get(i -1));
                lessonEnd = lessonStart.plusMinutes(lessonMinutesDuration);
                System.out.println(lessonEnd);
            }
            lessonStarts.add(lessonStart);
            lessonEnds.add(lessonEnd);
            lessonsInterim.put(lessonStart, lessonEnd);
        }

        return lessonsInterim;
    }

    public List<IterimsWrapper> getEventDateTimes
            (Map<LocalDateTime, LocalDateTime> lessonsInterim) {
        List<IterimsWrapper> iterims = new ArrayList<>();

        for (LocalDateTime startTime : lessonsInterim.keySet()) {
            iterims.add(new IterimsWrapper(
                    getEventDateTime(startTime),
                    getEventDateTime(lessonsInterim.get(startTime))
            ));
        }

        return iterims;
    }
}