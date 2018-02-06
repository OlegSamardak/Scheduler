package com.interlink.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.interlink.calendar.dto.TemplateDto;

import java.io.IOException;

public class TemplateDeserializer extends StdDeserializer<TemplateDto> {

    public TemplateDeserializer() {
        this(null);
    }

    protected TemplateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TemplateDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return null;
    }
}
