package com.interlink.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.interlink.calendar.dto.*;
import com.interlink.calendar.exceptions.InvalidCountOfBreaks;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateDeserializer extends StdDeserializer<TemplateDto> {

    @Autowired
    DeserializationService deserializationService;

    public TemplateDeserializer() {
        this(null);
    }

    private TemplateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TemplateDto deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        TemplateDto template = new TemplateDto();
        template.setWeeksCount(node.get("number_of_weeks").get("value").asInt());
        List<WeekDto> weeks = new ArrayList<>();
        try {
            for(int i = 0; i <= 1; i++) {
                weeks.add(deserializationService.getWeek(i, node));
            }
        } catch (InvalidCountOfBreaks invalidCountOfBreaks) {
            invalidCountOfBreaks.printStackTrace();
        }
        template.setWeeks(weeks);
        template.setGroupName(node.get("group").asText());

        return template;
    }
}