package com.interlink.calendar.dto;

import com.google.api.services.calendar.model.EventDateTime;

public class IterimsWrapper {

    private EventDateTime start;

    private EventDateTime end;

    public IterimsWrapper(EventDateTime start, EventDateTime end) {
        this.start = start;
        this.end = end;
    }

    public EventDateTime getStart() {
        return start;
    }

    public void setStart(EventDateTime start) {
        this.start = start;
    }

    public EventDateTime getEnd() {
        return end;
    }

    public void setEnd(EventDateTime end) {
        this.end = end;
    }
}