package com.interlink.authorization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.interlink.authorization.service.GoogleAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.google.api.services.calendar.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@RestController
public class GoogleAuthorizationController implements Serializable {

    @Autowired
    private GoogleAuthorizationService authorizationService;

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public String googleConnectionStatus(HttpServletRequest request) throws Exception {
        return "{\"response\": \""+authorizationService.authorize()+"\"}";
    }

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public ResponseEntity oauth2Callback(@RequestParam(value = "code") String code, HttpServletRequest request) throws IOException {
        Credential credential = authorizationService.oauth2Callback(code);
        HttpSession session = request.getSession();
        session.setAttribute("credential", credential);
        return new ResponseEntity(HttpStatus.OK);
    }
}