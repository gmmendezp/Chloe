package com.chloe.classifier;

import com.chloe.entities.Event;
import java.util.Collection;

/**
 * 
 * Classifiers need to implement this interface
 */
public interface EventClassifier {

    /**
     * Hydrate events with product ids
     * @param events
     */
    public void classify(Collection<Event> events);
}
