package com.interlink.schedule.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.calendar.dto.DayDto;
import com.interlink.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/template")
    public void createSchedule(@RequestBody DayDto day, HttpServletRequest request)
            throws IOException {
        HttpSession session = request.getSession(false);
        Credential credential = (Credential) session.getAttribute("credential");

        String calendarId = scheduleService.getCalendarId(day, credential);
        scheduleService.addEvents(day, calendarId, credential);
    }
}
