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
import java.util.Collection;

/**
 * Facebook event provider
 * Performs the login steps for obtaining permissions to facebook
 * Obtains facebook events of an user
 */
public class FacebookEventProvider implements EventProvider {

    private String token;
    private final String appId = "842168062543794";
    private final String appSecret = "3f2841ecec737d54855406328e059ae4";
    private FacebookClient facebookClient = new DefaultFacebookClient(
            Version.VERSION_2_3);
    private String redirectUri = "http://localhost:8084/" + "chloe" + "/widget.jsp";

    /**
     * Obtains the facebook login url
     * @return facebook login url
     */
    @Override
    public String getLoginUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_EVENTS);
        String facebookUrl = facebookClient.getLoginDialogUrl(appId,
                redirectUri, scopeBuilder);
        return facebookUrl;
    }

    /**
     * Code returned from the login url for permissions
     * @param code verification code
     */
    @Override
    public void login(String code) {
        AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, redirectUri, code);
        token = accessToken.getAccessToken();
        facebookClient = new DefaultFacebookClient(token, appSecret, Version.VERSION_2_3);
    }

    /**
     * Retrieves the list of events from a facebook user
     * @return events list
     */
    @Override
    public Collection<Event> getEvents() {
        Collection<Event> events = new ArrayList<Event>();
        Connection<com.restfb.types.Event> myEvents = facebookClient.fetchConnection("me/events", com.restfb.types.Event.class);
        for (com.restfb.types.Event e : myEvents.getData()) {
            Event event = new Event();
            event.setId(e.getId());
            event.setDescription(e.getDescription());
            event.setName(e.getName());
            events.add(event);
        }
        return events;
    }
}
