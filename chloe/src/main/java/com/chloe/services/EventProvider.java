package com.chloe.services;

import com.chloe.entities.Event;
import java.util.List;

public interface EventProvider {
    
    public String getLoginUrl(String redirectUri);
    public void login(String code);
    public List<Event> getEvents();
    
}
