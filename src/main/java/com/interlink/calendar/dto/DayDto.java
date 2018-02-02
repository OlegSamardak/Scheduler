package com.interlink.calendar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.interlink.deserialization.DayDeserializer;

import java.time.LocalDate;
import java.util.List;

@JsonDeserialize(using = DayDeserializer.class)
public class DayDto {

    private LocalDate localDate;

    private List<LessonDto> lessons;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "DayDto{" +
                "localDate=" + localDate +
                ", lessons=" + lessons +
                '}';
    }
}