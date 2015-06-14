package com.chloe.services;

import com.chloe.entities.Event;
import java.util.Collection;

/**
 * 
 * Event providers need to implement this interface
 * Most commonly a social networking site
 */
public interface EventProvider {

    /**
     * Obtains the login url to redirect the user to give permissions to the app
     * @return 
     */
    public String getLoginUrl();

    /**
     * Performs the login action
     * @param code code/token used to identify the user
     */
    public void login(String code);

    /**
     * Obtains the events
     * @return list of events of the user or another data source
     */
    public Collection<Event> getEvents();

}
