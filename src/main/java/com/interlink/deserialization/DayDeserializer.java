package com.interlink.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.api.services.calendar.model.EventDateTime;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.IterimsWrapper;
import com.interlink.calendar.dto.LessonDto;
import com.interlink.calendar.enums.LessonType;
import com.interlink.calendar.exceptions.InvalidCountOfBreaks;
import com.interlink.calendar.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class DayDeserializer extends StdDeserializer<DayDto> {

    @Autowired
    DateService dateService;

    public DayDeserializer() {
        this(null);
    }

    private DayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DayDto deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        List<LessonDto> lessons = new ArrayList<>();
        List<Integer> breaks = new ArrayList<>();
        Map<LocalDateTime, LocalDateTime> lessonsLocalDate = new TreeMap<>();
        DayDto day = new DayDto();
        int index = 0;

        for (JsonNode breakDuration : node.get("breaks")) {
            breaks.add(breakDuration.get("selectedValue").asInt());
        }

        LocalDate firstDay = new Timestamp(node.get("first_day").asLong())
                .toLocalDateTime()
                .toLocalDate();
        day.setLocalDate(firstDay);
        Integer lessonDuration = node.get("lesson_duration").asInt();

        try {
            lessonsLocalDate
                    = dateService.getLessonInterim(
                    LocalDateTime.of(
                            day.getLocalDate(),
                            LocalTime.parse(node.get("first_lesson").asText())
                    ),
                    breaks,
                    lessonDuration
            );
        } catch (InvalidCountOfBreaks invalidCountOfBreaks) {
            invalidCountOfBreaks.printStackTrace();
        }

        List<IterimsWrapper> iterims = dateService.getEventDateTimes(lessonsLocalDate);

        for (JsonNode lessonNode : node.get("schedule_template").get(0).get(0)) {
            IterimsWrapper iterimsWrapper = iterims.get(index);

            LessonDto lesson
                    = new LessonDto.Builder(
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
        day.setGroupName(node.get("group").asText());
        day.setLessons(lessons);

        return day;
    }
}