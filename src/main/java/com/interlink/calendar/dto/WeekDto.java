package com.interlink.calendar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.interlink.calendar.enums.WeekType;
import com.interlink.deserialization.TemplateDeserializer;

import java.util.List;

public class WeekDto {

    private WeekType weekType;

    private List<DayDto> days;

    public List<DayDto> getDays() {
        return days;
    }

    public void setDays(List<DayDto> days) {
        this.days = days;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }
}
