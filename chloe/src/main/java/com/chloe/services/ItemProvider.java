package com.chloe.services;

import com.chloe.entities.Event;
import java.util.Collection;

public interface ItemProvider {
    
    public void getItems(Collection<Event> events);
}
