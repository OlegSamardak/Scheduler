package com.interlink.authorization.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.authorization.service.GoogleAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@RestController
public class GoogleAuthorizationController implements Serializable {

    private final ServletContext context;

    @Autowired
    private GoogleAuthorizationService authorizationService;

    @Autowired
    public GoogleAuthorizationController(ServletContext context) {
        this.context = context;
    }

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public String googleConnectionStatus(HttpServletRequest request) throws Exception {
        return "{\"response\": \""+authorizationService.authorize()+"\"}";
    }

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public ResponseEntity oauth2Callback(@RequestParam(value = "code") String code, HttpServletRequest request)
            throws IOException {
        Credential credential = authorizationService.oauth2Callback(code);

        System.out.println("Login AT: "+credential.getAccessToken());
        HttpSession session = request.getSession();
        session.setAttribute("credential", credential);
        context.setAttribute("credential", credential);
        return new ResponseEntity(HttpStatus.OK);
    }
}