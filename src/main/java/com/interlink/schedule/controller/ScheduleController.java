package com.interlink.schedule.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.calendar.dto.DayDto;
import com.interlink.entity.Group;
import com.interlink.entity.Schedule;
import com.interlink.group.service.GroupValidationService;
import com.interlink.schedule.repository.ScheduleRepository;
import com.interlink.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ScheduleController {

    private final ServletContext context;

    private final GroupValidationService groupValidationService;

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,
                              GroupValidationService groupValidationService, ServletContext context) {
        this.scheduleService = scheduleService;
        this.groupValidationService = groupValidationService;
        this.context = context;
    }

    @CrossOrigin
    @PostMapping("/template")
    public void createSchedule(@RequestBody DayDto day, HttpServletRequest request)
            throws IOException {
        Group group = new Group();
        Schedule schedule = new Schedule();
        //HttpSession session = request.getSession();
        Credential credential = (Credential) context.getAttribute("credential");
        String calendarId = scheduleService.getCalendarId(day, credential);
        scheduleService.addEvents(day, calendarId, credential);

        group.setName(day.getGroupName());
        schedule.setGroup(group);
        schedule.setCalendarId(calendarId);

        groupValidationService.saveNewGroup(group);
        scheduleService.saveSchedule(schedule);
    }
}