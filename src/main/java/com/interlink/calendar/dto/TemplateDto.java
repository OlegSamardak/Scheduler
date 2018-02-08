package com.interlink.calendar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.interlink.deserialization.TemplateDeserializer;

import java.util.List;

@JsonDeserialize(using = TemplateDeserializer.class)
public class TemplateDto {

    private String groupName;

    private Integer weeksCount;

    private List<WeekDto> weeks;

    public Integer getWeeksCount() {
        return weeksCount;
    }

    public void setWeeksCount(Integer weeksCount) {
        this.weeksCount = weeksCount;
    }

    public List<WeekDto> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<WeekDto> weeks) {
        this.weeks = weeks;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "TemplateDto{" +
                "groupName='" + groupName + '\'' +
                ", weeksCount=" + weeksCount +
                ", weeks=" + weeks +
                '}';
    }
}
