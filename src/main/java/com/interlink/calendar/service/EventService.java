package com.interlink.calendar.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.LessonDto;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    public com.google.api.services.calendar.model.Event createLessonEvent
            (Credential credential, LessonDto lessonDto) {
        com.google.api.services.calendar.model.Event lessonEvent
                = new com.google.api.services.calendar.model.Event();
        lessonEvent.setStart(lessonDto.getStart());
        lessonEvent.setEnd(lessonDto.getEnd());
        lessonEvent.setSummary(lessonDto.getTeacherName());
        lessonEvent.setDescription(lessonDto.getTitle() + "lesson in " + lessonDto.getClassroom()
                + " classroom" + "\n Teacher : " + lessonDto.getTeacherName()
                + ". Lesson type : " + lessonDto.getType());

        return lessonEvent;
    }
}