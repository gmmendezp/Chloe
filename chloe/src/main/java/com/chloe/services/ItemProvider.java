package com.chloe.services;

import com.chloe.entities.Item;
import java.util.Collection;

public interface ItemProvider {
    
    public Collection<Item> getItems(Collection<String> itemIds);
}
