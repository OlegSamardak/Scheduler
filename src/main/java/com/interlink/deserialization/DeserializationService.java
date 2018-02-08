package com.interlink.deserialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.IterimsWrapper;
import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.dto.WeekDto;
import com.interlink.calendar.exceptions.InvalidCountOfBreaks;
import com.interlink.calendar.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeserializationService {

    private final DateService dateService;

    @Autowired
    public DeserializationService(DateService dateService) {
        this.dateService = dateService;
    }

    public List<Integer> getBreaks(JsonNode node) {
        List<Integer> breaks = new ArrayList<>();
        for (JsonNode breakDuration : node.get("breaks")) {
            breaks.add(breakDuration.get("selectedValue").asInt());
        }
        return breaks;
    }

    public WeekDto getWeek(int weekPosition, JsonNode node)
            throws InvalidCountOfBreaks {
        WeekDto week = new WeekDto();
        List<DayDto> weekDays = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            DayDto day = new DayDto();
            day.setLessons(getLessons(node, weekPosition, i));
            weekDays.add(day);
        }
        week.setDays(weekDays);
        return week;
    }

    private List<LessonDto> getLessons(JsonNode node, int weekIndex, int dayIndex)
            throws InvalidCountOfBreaks {
        List<LessonDto> lessons = new ArrayList<>();
        Map<LocalDateTime, LocalDateTime> lessonsLocalDate;
        LocalDate firstDay = new Timestamp(node.get("first_day")
                .asLong()).toLocalDateTime().toLocalDate();
        List<LocalDate> upperWeek = dateService.getDays(firstDay);
        lessonsLocalDate = dateService.getLessonInterim(
                LocalDateTime.of(
                        upperWeek.get(dayIndex),
                        LocalTime.parse(node.get("first_lesson").asText())
                ),
                getBreaks(node),
                node.get("lesson_duration").asInt()
        );
        List<IterimsWrapper> iterims = dateService.getEventDateTimes(lessonsLocalDate);
        int index = 0;
        for (JsonNode lessonNode : node.get("schedule_template").get(weekIndex).get(dayIndex)) {
            IterimsWrapper iterimsWrapper = iterims.get(index);
            LessonDto lesson = new LessonDto.Builder(
                    lessonNode.get("subject").asText(),
                    iterimsWrapper.getStart(),
                    iterimsWrapper.getEnd()
            )
                    .classroom(lessonNode.get("lectureHall").asText())
                    .teacher(lessonNode.get("teacher").asText())
                    .type(lessonNode.get("lessonType").asText())
                    .build();
            lessons.add(lesson);
            index++;
        }
        return lessons;
    }
}
