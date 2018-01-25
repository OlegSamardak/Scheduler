package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.LessonDTO;

public class EventService {

    public com.google.api.services.calendar.model.Event createLessonEvent
            (Credential credential, LessonDTO lessonDTO) {
        com.google.api.services.calendar.model.Event lessonEvent
                = new com.google.api.services.calendar.model.Event();
        lessonEvent.setStart(new EventDateTime().setDateTime(lessonDTO.getStart()));
        lessonEvent.setEnd(new EventDateTime().setDateTime(lessonDTO.getEnd()));
        lessonEvent.setSummary(lessonDTO.getTeacherName());
        lessonEvent.setDescription(lessonDTO.getTitle() + "lessonDTO in " + lessonDTO.getClassroom()
                + " classroom" + "\n Teacher : " + lessonDTO.getTeacherName());

        return lessonEvent;
    }
}