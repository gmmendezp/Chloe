package com.chloe.services;

import com.chloe.entities.Item;
import java.util.List;

public interface ItemProvider {
    
    public List<Item> getItems(List<String> itemIds);
}
