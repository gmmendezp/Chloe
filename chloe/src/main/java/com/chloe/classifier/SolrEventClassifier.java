package com.chloe.classifier;

import com.chloe.config.ChloeConfig;
import com.chloe.entities.Event;
import com.chloe.entities.Item;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

public class SolrEventClassifier implements EventClassifier {

    private static final String OR = " OR ";
    private static final String ACTIVITIES_FIELD = "activities:{keyword}";
    private static final String URL = "http://54.152.144.85:8983/solr/chloe-index/select?q={query}&wt=json&indent=true";

    @Override
    public void classify(Collection<Event> events) {
        Set<String> keywords = new HashSet<String>();

        for (Event event : events) {
            try {
                String eventName = event.getName();
                Collection<Item> items = event.getItems();
                if (!StringUtils.isBlank(eventName)) {
                    String[] tokensArray = StringUtils.split(eventName, " ");
                    Set<String> tokens = new HashSet<String>();
                    for (String t : tokensArray) {
                        tokens.add(URLEncoder.encode(t, "UTF-8"));
                    }
                    String preparedUrl = URL.replaceAll("\\{query\\}", generateEventQuery(tokens));
                    System.out.println(String.format("Making request [%s]", preparedUrl));
                    items.addAll(processClassifierResult(ChloeConfig.getInstance().getRestTemplate().getForObject(preparedUrl, String.class)));
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String generateEventQuery(Set<String> eventNameParts) {
        StringBuilder builder = new StringBuilder();

        for (String eventNamePart : eventNameParts) {
            if (!StringUtils.isBlank(builder)) {
                builder.append(OR);
            }
            builder.append(ACTIVITIES_FIELD.replaceAll("\\{keyword\\}", eventNamePart));
        }

        return builder.toString();
    }

    public Set<Item> processClassifierResult(String resultString) {
        try {
            ObjectNode jsonObject = ChloeConfig.getInstance().getObjectMapper(resultString);
            JsonNode docs = jsonObject.get("response").get("docs");

            Iterator<JsonNode> it = docs.iterator();
            Set<Item> result = new HashSet<>();

            while (it.hasNext()) {
                result.addAll(getItemsFromDoc(it.next()));
            }

            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Collection<Item> getItemsFromDoc(JsonNode docs) {
        JsonNode items = docs.get("product");
        Iterator<JsonNode> it = items.iterator();

        Collection list = new ArrayList();
        while (it.hasNext()) {
            list.add(new Item(it.next().getTextValue()));
        }

        return list;
    }

}
