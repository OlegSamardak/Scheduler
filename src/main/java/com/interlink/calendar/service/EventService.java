package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.LessonDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.TimeZone;

@Service
public class  EventService {

    public com.google.api.services.calendar.model.Event createLessonEvent
            (LessonDto lessonDto, int weekCount) {
        com.google.api.services.calendar.model.Event lessonEvent
                = new com.google.api.services.calendar.model.Event();
        lessonEvent.setStart(lessonDto.getStart().setTimeZone(TimeZone.getDefault().getID()));
        lessonEvent.setEnd(lessonDto.getEnd().setTimeZone(TimeZone.getDefault().getID()));
        lessonEvent.setSummary(lessonDto.getTitle());
        lessonEvent.setDescription(lessonDto.getTitle() + " lesson in " + lessonDto.getClassroom()
                + " classroom" + "\n Teacher : " + lessonDto.getTeacherName()
                + ". \n Lesson type : " + lessonDto.getType());
        lessonEvent.setRecurrence(Arrays.asList("RRULE:FREQ=WEEKLY;COUNT=" + weekCount + ";INTERVAL=2"));

        return lessonEvent;
    }
}