package com.interlink.authorization.service;


import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoogleAuthorizationService {
    private final static Log LOGGER = LogFactory.getLog(GoogleAuthorizationService.class);
    private static HttpTransport HTTP_TRANSPORT;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private GoogleClientSecrets clientSecrets;
    private GoogleAuthorizationCodeFlow flow;

    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;

    public String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                    Collections.singleton(CalendarScopes.CALENDAR)).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
        LOGGER.warn(authorizationUrl.build());
        return authorizationUrl.build();
    }

    public Credential oauth2Callback(String code) {
        Credential credential;
        try {
            TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
            credential = flow.createAndStoreCredential(response, "userID");
            return credential;
        } catch (Exception e) {
            LOGGER.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ").");
        }
        return null;
    }
}