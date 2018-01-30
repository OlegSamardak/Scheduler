package com.interlink.calendar.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DayDto {

    private LocalDate localDate;

    private DayOfWeek dayOfWeek;

    private List<LessonDto> lessons;
}