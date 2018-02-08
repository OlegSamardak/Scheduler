package com.interlink.calendar.dto;

import java.util.List;

public class DayDto {

    private List<LessonDto> lessons;

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }
}