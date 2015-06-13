package com.chloe.services;


import com.chloe.entities.Event;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.scope.UserDataPermissions;
import com.restfb.scope.ScopeBuilder;
import java.util.ArrayList;
import java.util.List;

public class FacebookEventProvider implements EventProvider{

    private String token;
    private final String appId = "842168062543794";
    private final String appSecret = "3f2841ecec737d54855406328e059ae4";
    private FacebookClient facebookClient = new DefaultFacebookClient(
                Version.VERSION_2_3);
    private String redirectUri = "http://54.152.144.85:8080/chloe/events.jsp";
    
    @Override
    public String getLoginUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_ABOUT_ME);
        scopeBuilder.addPermission(UserDataPermissions.USER_EVENTS);
        String facebookUrl = facebookClient.getLoginDialogUrl(appId,
                redirectUri, scopeBuilder);
        return facebookUrl;
    }
    
    @Override
    public void login(String code) {
        AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, redirectUri, code);
        token = accessToken.getAccessToken();
        facebookClient = new DefaultFacebookClient(token, appSecret, Version.VERSION_2_3);
    }
    
    @Override
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<Event>();
        Connection<com.restfb.types.Event> myEvents = facebookClient.fetchConnection("me/events", com.restfb.types.Event.class);
        for(com.restfb.types.Event e : myEvents.getData()) {
            Event event = new Event();
            event.setId(e.getId());
            event.setDescription(e.getDescription());
            event.setName(e.getName());
        }
        return events;
    }
    
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }   
}
