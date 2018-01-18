package com.interlink.authorization.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.authorization.service.GoogleAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GoogleAuthorizationController {
    @Autowired
    GoogleAuthorizationService authorizationService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public void authorize() throws IOException{
        Credential credential = authorizationService.authorize();
    }

}
