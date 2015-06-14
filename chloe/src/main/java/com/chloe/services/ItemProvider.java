package com.chloe.services;

import com.chloe.entities.Event;
import java.util.Collection;
/**
 * 
 * Item providers need to implement this interface
 * Most commonly a store
 */
public interface ItemProvider {
    
    /**
     * Hydrates the events with the information of the items
     * @param events events from a social network or another source
     */
    public void getItems(Collection<Event> events);
}
