package com.chloe.services;

import com.chloe.config.ChloeConfig;
import com.chloe.entities.Event;
import com.chloe.entities.Item;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.client.RestTemplate;
/**
 *
 * BackCountry item/product provider
 * Obtains product info from backcountry's API
 */
public class BackcountryItemProvider implements ItemProvider {

    /**
     * Hydrates the events with the item/product information, obtained from
     * backcountry's API
     * @param events events from a social network or any other source
     */
    @Override
    public void getItems(Collection<Event> events) {
        RestTemplate rt = ChloeConfig.getInstance().getRestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("site", "bcs");
        for (Event e : events) {
            Set<String> list = new HashSet<String>();
            Map<String, Item> map = new HashMap<String, Item>();
            List<Item> items = e.getItems();
            for (Item i : items) {
                list.add(i.getId());
                map.put(i.getId(), i);
            }
            String ids = StringUtils.join(list, ",");
            String itemsJson = rt.getForObject(
                    "http://productapipqa-vip.bcinfra.net:9000/v1/products/"
                    + ids, String.class, params);
            getItemsFromJson(itemsJson, map);
        }
    }

    /**
     * Parses the response from the API to obtain the items information
     * @param itemsJson json response of the API
     * @param items items obtained from an event, only item.id is necessary
     */
    private void getItemsFromJson(String itemsJson, Map<String, Item> items) {
        try {
            ObjectNode jsonObject = ChloeConfig.getInstance().getObjectMapper(itemsJson);
            JsonNode products = jsonObject.get("products");

            Iterator<JsonNode> it = products.iterator();
            while (it.hasNext()) {
                JsonNode itemJson = it.next();
                if (itemJson.get("id") != null) {
                    String id = itemJson.get("id").getTextValue();
                    if (items.containsKey(id)) {
                        Item i = items.get(id);
                        i.update(itemJson);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(BackcountryItemProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
