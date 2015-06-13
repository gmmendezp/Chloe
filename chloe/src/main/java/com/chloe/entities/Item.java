package com.chloe.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.JsonNode;

public class Item {
    
    private String id;
    private String title;
    private String description;

    public Item(JsonNode itemJson) {
        id = itemJson.get("id").getTextValue();
        title = itemJson.get("title").getTextValue();
        description = itemJson.get("description").getTextValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
