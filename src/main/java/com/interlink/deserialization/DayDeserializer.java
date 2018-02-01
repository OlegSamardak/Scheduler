package com.interlink.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.LessonDto;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayDeserializer extends StdDeserializer<DayDto> {

    protected DayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DayDto deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        DayDto dayDto = new DayDto();
        JsonNode node = parser.getCodec().readTree(parser);
        LocalDate localDate = new Timestamp(node.get("firstday")
                .asLong())
                .toLocalDateTime()
                .toLocalDate();

        return dayDto;
    }
}