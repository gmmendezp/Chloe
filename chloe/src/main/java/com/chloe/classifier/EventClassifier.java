package com.chloe.classifier;

import com.chloe.entities.Event;
import java.util.Collection;

public interface EventClassifier {
        
    public Collection<String> classify(Collection<Event> events);
}
