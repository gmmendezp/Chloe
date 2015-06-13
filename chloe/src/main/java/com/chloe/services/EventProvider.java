package com.chloe.services;

import com.chloe.entities.Event;
import java.util.Collection;

public interface EventProvider {

    public String getLoginUrl();

    public void login(String code);

    public Collection<Event> getEvents();

}
