package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.LessonDto;

public class EventService {

    public com.google.api.services.calendar.model.Event createLessonEvent
            (Credential credential, LessonDto lessonDto) {
        com.google.api.services.calendar.model.Event lessonEvent
                = new com.google.api.services.calendar.model.Event();
        lessonEvent.setStart(new EventDateTime().setDateTime(lessonDto.getStart()));
        lessonEvent.setEnd(new EventDateTime().setDateTime(lessonDto.getEnd()));
        lessonEvent.setSummary(lessonDto.getTeacherName());
        lessonEvent.setDescription(lessonDto.getTitle() + "lessonDto in " + lessonDto.getClassroom()
                + " classroom" + "\n Teacher : " + lessonDto.getTeacherName());

        return lessonEvent;
    }
}