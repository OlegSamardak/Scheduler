package com.interlink.authorization.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.interlink.authorization.service.GoogleAuthorizationService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.util.ajax.JSONDateConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class GoogleAuthorizationController implements Serializable{
    @Autowired
    private GoogleAuthorizationService authorizationService;

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
        return new RedirectView(authorizationService.authorize());
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public @ResponseBody Credential oauth2Callback(@RequestParam(value = "code") String code) {
        Credential credential = authorizationService.oauth2Callback(code);

        return credential;
    }

}