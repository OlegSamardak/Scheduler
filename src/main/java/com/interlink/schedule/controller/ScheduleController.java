package com.interlink.schedule.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.authorization.service.GoogleAuthorizationService;
import com.interlink.calendar.dto.DayDto;
import com.interlink.calendar.dto.TemplateDto;
import com.interlink.entity.Group;
import com.interlink.entity.Schedule;
import com.interlink.group.service.GroupValidationService;
import com.interlink.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ScheduleController {

    private final GroupValidationService groupValidationService;

    private final ScheduleService scheduleService;

    private final GoogleAuthorizationService authorizationService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,
                              GroupValidationService groupValidationService,
                              GoogleAuthorizationService authorizationService) {
        this.scheduleService = scheduleService;
        this.groupValidationService = groupValidationService;
        this.authorizationService = authorizationService;
    }

    @CrossOrigin
    @PostMapping(path = "/template", params = "code")
    public void createSchedule(@RequestParam(value = "code") String code,
                               @RequestBody TemplateDto template)
            throws IOException {
        Group group = new Group();

        Schedule schedule = new Schedule();
        Credential credential = authorizationService.oauth2Callback(code);
        String calendarId = scheduleService.getCalendarId(template, credential);
        scheduleService.addEvents(template, calendarId, credential);

        group.setName(template.getGroupName());
        schedule.setGroup(group);
        schedule.setCalendarId(calendarId);

        groupValidationService.saveNewGroup(group);
        scheduleService.saveSchedule(schedule);
    }
}