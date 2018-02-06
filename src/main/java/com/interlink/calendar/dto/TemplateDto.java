package com.interlink.calendar.dto;

import com.interlink.calendar.enums.WeekType;

import java.util.Map;

public class TemplateDto {

    private Integer weeksCount;

    private Map<WeekType, WeekDto> weekForPosition;

    public Integer getWeeksCount() {
        return weeksCount;
    }

    public void setWeeksCount(Integer weeksCount) {
        this.weeksCount = weeksCount;
    }

    public Map<WeekType, WeekDto> getWeekForPosition() {
        return weekForPosition;
    }

    public void setWeekForPosition(Map<WeekType, WeekDto> weekForPosition) {
        this.weekForPosition = weekForPosition;
    }
}
