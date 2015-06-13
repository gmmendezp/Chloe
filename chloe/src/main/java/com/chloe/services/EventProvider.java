package com.chloe.services;

import com.chloe.entities.Event;
import java.util.List;

public interface EventProvider {    
    public String login();
    public List<Event> getEvents();
    
}
