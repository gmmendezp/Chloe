package com.chloe.entities;

import java.util.Iterator;
import org.codehaus.jackson.JsonNode;

public class Item {

    private String id;
    private String title;
    private String description;
    private String brand;
    private String image;
    private Double listPrice;
    private Double salePrice;
    private boolean onSale;
    private String price;
    private String url;
    private int[] rate;

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

                if (sku.get("onSale") != null) {
                    onSale = sku.get("onSale").getBooleanValue();
                }
                listPrice = sku.get("listPrice").getDoubleValue();
                salePrice = sku.get("salePrice").getDoubleValue();

                if (listPrice.equals(salePrice)) {
                    price = "$" + listPrice;
                } else if (onSale) {
                    price = "<span class='salePrice'>sale $" + salePrice + "</span> <span class='listPriceSale'>" + "$" + listPrice + "</span>";
                } else {
                    price = "$" + listPrice + " - " + "$" + salePrice;
                }

                url = sku.get("url").getTextValue();
                
                
                
                if (sku.get("image") != null) {
                    image = sku.get("image").get("url").getTextValue();
                    break;
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int[] getRate() {
        return rate;
    }

    public void setRate(int[] rate) {
        this.rate = rate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
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
        return "Item{" + "id=" + id + ", title=" + title + ", description=" + description + ", brand=" + brand + ", image=" + image + ", listPrice=" + listPrice + ", salePrice=" + salePrice + ", onSale=" + onSale + ", price=" + price + ", url=" + url + ", rate=" + rate + '}';
    }

}
