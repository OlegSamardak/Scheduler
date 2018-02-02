package com.interlink.calendar.dto;

import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.enums.LessonType;

public class LessonDto {

    private final String type;

    private final String teacherName;

    private final String title;

    private final String classroom;

    private final EventDateTime start;

    private final EventDateTime end;

    public static class Builder {

        private String type;

        private String teacherName;

        private String title;

        private String classroom;

        private EventDateTime start;

        private EventDateTime end;

        public Builder(String title, EventDateTime start, EventDateTime end) {
            this.title = title;
            this.start = start;
            this.end = end;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder teacher(String val) {
            teacherName = val;
            return this;
        }

        public Builder classroom(String val) {
            classroom = val;
            return this;
        }

        public LessonDto build() {
            return new LessonDto(this);
        }
    }

    private LessonDto(Builder builder) {
        type = builder.type;
        teacherName = builder.teacherName;
        title = builder.title;
        classroom = builder.classroom;
        start = builder.start;
        end = builder.end;
    }

    public String getType() {
        return type;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTitle() {
        return title;
    }

    public String getClassroom() {
        return classroom;
    }

    public EventDateTime getStart() {
        return start;
    }

    public EventDateTime getEnd() {
        return end;
    }


}