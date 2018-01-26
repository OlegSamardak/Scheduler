package com.interlink.calendar.dto;

import com.google.api.client.util.DateTime;
import com.interlink.calendar.enums.LessonType;

public class LessonDto {

    private final LessonType type;

    private final String teacherName;

    private final String title;

    private final String classroom;

    private final DateTime start;

    private final DateTime end;

    public static class Builder {
        private LessonType type;

        private String teacherName;

        private String title;

        private String classroom;

        private DateTime start;

        private DateTime end;

        public Builder(String title, DateTime start, DateTime end) {
            this.title = title;
            this.start = start;
            this.end = end;
        }

        public Builder type(LessonType val) {
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

    public LessonType getType() {
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

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
        return end;
    }
}