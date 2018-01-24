package com.interlink.authorization.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.google.api.client.auth.oauth2.Credential;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GoogleAuthorizationController {

    GoogleAuthorizationService authorizationService;
    AuthenticationController controller;

    @Autowired
    public GoogleAuthorizationController(GoogleAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        this.controller = AuthenticationController.newBuilder(
                "university-scheduler.eu.auth0.com",
                "d55nVilcpllMHHFt_tgnlP8e7X8cu5Z-",
                "CRT9Kxk3GOB82L8jRdNYYBGq9Y7sYSSIBDLDyklsLQ5eFlUcf7UIwak6fs2r0jPf"
        )
                .build();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public void authorize(HttpServletRequest request, HttpServletResponse response)
            throws IOException, IdentityVerificationException {

        String authorizeUrl
                = controller.buildAuthorizeUrl(request, "http://localhost:8080/abc")
                .build();


        response.sendRedirect(authorizeUrl);
    }

    @RequestMapping(path = "/abc", method = RequestMethod.GET)
    public String abc(HttpServletRequest request, HttpServletResponse response) throws IdentityVerificationException, IOException {
        Tokens tokens = controller.handle(request);
        SessionUtils.set(request, "token", tokens.getAccessToken());

        return "Token :" + request.getSession(false).getAttribute("token");
    }
}