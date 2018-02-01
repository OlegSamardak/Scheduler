package com.interlink.calendar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.enums.LessonType;
import com.interlink.deserialization.DayDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonDeserialize(using = DayDeserializer.class)
public class DayDto {

    private LocalDate localDate;

    private DayOfWeek dayOfWeek;

    private List<Integer> breaks = new ArrayList<>();

    private List<LessonDto> lessons = new ArrayList<>();
}