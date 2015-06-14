package com.chloe.classifier;

import com.chloe.entities.Event;
import java.util.Collection;

public interface EventClassifier {

    public void classify(Collection<Event> events);
}
