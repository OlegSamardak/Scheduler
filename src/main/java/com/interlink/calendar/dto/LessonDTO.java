package com.interlink.calendar.dto;

import com.google.api.client.util.DateTime;
import com.interlink.calendar.enums.LessonType;

public class LessonDTO {

    private LessonType type;

    private String teacherName;

    private String title;

    private String classroom;

    private DateTime start;

    private DateTime end;

    public LessonDTO() {

    }

    public LessonDTO(String classroom, DateTime start, DateTime end) {
        this.classroom = classroom;
        this.start = start;
        this.end = end;
    }

    public LessonDTO(LessonType lessonType, String teacherName,
                     String lessonTitle, String classroom, DateTime start, DateTime end) {
        this.type = lessonType;
        this.teacherName = teacherName;
        this.title = lessonTitle;
        this.classroom = classroom;
        this.start = start;
        this.end = end;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "type=" + type +
                ", teacherName='" + teacherName + '\'' +
                ", title='" + title + '\'' +
                ", classroom='" + classroom + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
