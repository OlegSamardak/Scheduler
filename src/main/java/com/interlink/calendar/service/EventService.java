package com.interlink.calendar.service;

import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.enums.LessonType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.TimeZone;

@Service
public class EventService {

    public com.google.api.services.calendar.model.Event createLessonEvent
            (LessonDto lessonDto, int weekCount) {
        com.google.api.services.calendar.model.Event lessonEvent
                = new com.google.api.services.calendar.model.Event();
        lessonEvent.setStart(lessonDto.getStart().setTimeZone(TimeZone.getDefault().getID()));
        lessonEvent.setEnd(lessonDto.getEnd().setTimeZone(TimeZone.getDefault().getID()));
        lessonEvent.setSummary(lessonDto.getTitle());
        lessonEvent.setDescription("Пара - " + lessonDto.getTitle() + ", в " + lessonDto.getClassroom()
                + " аудиторії" + "\nВикладач : " + lessonDto.getTeacherName()
                + ". \nТип пари : " + getLessonType(lessonDto.getType()));
        lessonEvent.setRecurrence(Arrays.asList("RRULE:FREQ=WEEKLY;COUNT=" + weekCount + ";INTERVAL=2"));
        lessonEvent.setColorId(getColorId(lessonDto.getType()));

        return lessonEvent;
    }

    private String getColorId(LessonType type){
        switch (type) {
            case LECTION :
                return "2";
            case PRACTICE:
                return "7";
            case SEMINAR:
                return "5";
            case LABORATORY_WORK:
                return "4";
            default:
                return "6";
        }
    }

    private String getLessonType(LessonType lessonType) {
        switch (lessonType) {
            case LECTION:
                return "Лекція";
            case PRACTICE:
                return "Практичне заняття";
            case SEMINAR:
                return "Семінар";
            case LABORATORY_WORK:
                return "Лабораторна робота";
            default:
                return "Самостійна робота";
        }
    }
}