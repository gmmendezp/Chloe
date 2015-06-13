package com.chloe.services;

import com.chloe.config.ChloeConfig;
import com.chloe.entities.Item;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.client.RestTemplate;

public class BackcountryItemProvider implements ItemProvider {

    @Override
    public List<Item> getItems(List<String> itemIds) {
        RestTemplate rt = ChloeConfig.getInstance().getRestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("site", "bcs");
        String ids = StringUtils.join(itemIds, ",");
        String itemsJson = rt.getForObject("http://productapipqa-vip.bcinfra.net:9000/v1/products/" + ids, String.class, params);
        return getItemsFromJson(itemsJson);
    }

    private List<Item> getItemsFromJson(String itemsJson) {
        List<Item> items = new ArrayList<Item>();
        try {
            ObjectNode jsonObject = ChloeConfig.getInstance().getObjectMapper(itemsJson);
            JsonNode products = jsonObject.get("products");
            
            Iterator<JsonNode> it = products.iterator();
            while(it.hasNext()) {
                JsonNode itemJson = it.next();
                Item item = new Item(itemJson);
                items.add(item);
            }
            return items;
        } catch (IOException ex) {
            Logger.getLogger(BackcountryItemProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    
    
}
