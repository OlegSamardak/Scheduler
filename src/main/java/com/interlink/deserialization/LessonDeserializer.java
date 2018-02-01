package com.interlink.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.interlink.calendar.dto.LessonDto;

import java.io.IOException;

public class LessonDeserializer extends StdDeserializer<LessonDto> {

    protected LessonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LessonDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return null;
    }
}
