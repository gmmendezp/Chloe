package com.chloe.services;

import com.chloe.entities.Event;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.UserDataPermissions;
import com.restfb.scope.ScopeBuilder;
import java.util.ArrayList;
import java.util.List;

public class FacebookEventProvider implements EventProvider{

    private String token;
    
    @Override
    public String login() {
        FacebookClient facebookClient = new DefaultFacebookClient(
                Version.VERSION_2_2);
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_ABOUT_ME);
        scopeBuilder.addPermission(UserDataPermissions.USER_EVENTS);
        String facebookUrl = facebookClient.getLoginDialogUrl("842168062543794",
                "http://52.6.163.58:8080/chloe-prod/events", scopeBuilder);
        return facebookUrl;
    }
    
    @Override
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<Event>();
        return events;
    }
}
