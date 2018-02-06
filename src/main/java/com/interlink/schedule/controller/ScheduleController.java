package com.interlink.schedule.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.interlink.authorization.service.GoogleAuthorizationService;
import com.interlink.calendar.dto.DayDto;
import com.interlink.entity.Group;
import com.interlink.entity.Schedule;
import com.interlink.group.service.GroupValidationService;
import com.interlink.schedule.repository.ScheduleRepository;
import com.interlink.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class ScheduleController {

    //    private final ServletContext context;
    private static final String APPLICATION_NAME = "";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    private final GroupValidationService groupValidationService;
    private static com.google.api.services.calendar.Calendar client;

    private final ScheduleService scheduleService;

    @Autowired
    GoogleAuthorizationService authorizationService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,
                              GroupValidationService groupValidationService) {
        this.scheduleService = scheduleService;
        this.groupValidationService = groupValidationService;
    }

    @CrossOrigin
    @PostMapping(path = "/template", params = "code")
    public void createSchedule(@RequestParam(value = "code") String code,
                               @RequestBody DayDto day, HttpServletRequest request)
            throws IOException {
        Group group = new Group();

        Schedule schedule = new Schedule();
        Credential credential = authorizationService.oauth2Callback(code);
        System.out.println("template AT: " + credential.getAccessToken());
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();


        String calendarId = scheduleService.getCalendarId(day, credential);
        scheduleService.addEvents(day, calendarId, credential);

        group.setName(day.getGroupName());
        schedule.setGroup(group);
        schedule.setCalendarId(calendarId);

        groupValidationService.saveNewGroup(group);
        scheduleService.saveSchedule(schedule);
    }
}