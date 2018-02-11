package com.interlink.authorization.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.interlink.authorization.service.GoogleAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@RestController
public class GoogleAuthorizationController implements Serializable {

    private GoogleAuthorizationService authorizationService;

    @Autowired
    public GoogleAuthorizationController(GoogleAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public String googleConnectionStatus(HttpServletRequest request) throws Exception {
        return "{\"response\": \"" + authorizationService.authorize() + "\"}";
    }

    @CrossOrigin
    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public ResponseEntity oauth2Callback(@RequestParam(value = "code") String code,
                                         HttpServletRequest request)
            throws IOException {
        Credential credential = authorizationService.oauth2Callback(code);

        return new ResponseEntity(HttpStatus.OK);
    }
}