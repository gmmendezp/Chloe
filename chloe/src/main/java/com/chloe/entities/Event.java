package com.chloe.entities;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String id;
    private String name;
    private String description;
    private List<Item> items = new ArrayList<Item>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Event() {
    }
    
    public Event(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
