package com.chloe.entities;

import java.util.Iterator;
import org.codehaus.jackson.JsonNode;

public class Item {

    private String id;
    private String title;
    private String description;
    private String brand;
    private String image;

    public Item(String id) {
        this.id = id;
    }

    public void update(JsonNode itemJson) {
        title = itemJson.get("title").getTextValue();
        description = itemJson.get("description").getTextValue();

        if (itemJson.get("brand") != null) {
            brand = itemJson.get("brand").get("name").getTextValue();
        }

        if (itemJson.get("skus") != null) {
            for (Iterator<JsonNode> it = itemJson.get("skus").iterator(); it.hasNext();) {
                JsonNode sku = it.next();

                if (sku.get("image") != null) {
                    image = sku.get("image").get("url").getTextValue();
                    break;
                }
            }
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", title=" + title + ", description=" + description + ", brand=" + brand + ", image=" + image + '}';
    }

}
