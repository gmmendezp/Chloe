package com.chloe.classifier;

import com.chloe.config.ChloeConfig;
import com.chloe.entities.Event;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

public class SolrEventClassifier implements EventClassifier {

    private static final String OR = " OR ";
    private static final String ACTIVITIES_FIELD = "activities:{keyword}";
    private static final String URL = "http://54.152.144.85:8983/solr/chloe-index/select?q={query}&wt=json&indent=true";

    @Override
    public Set<String> classify(Collection<Event> events) {
        Set<String> keywords = new HashSet<String>();

        for (Event event : events) {
            try {
                String eventName = event.getName();
                if (!StringUtils.isBlank(eventName)) {
                    String[] tokensArray = StringUtils.split(eventName, " ");
                    List<String> tokens = new ArrayList<String>();
                    for(String t : tokensArray) {
                        tokens.add(URLEncoder.encode(t, "UTF-8"));
                    }
                    keywords.addAll(tokens);
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }

        }
        if (!keywords.isEmpty()) {
            String preparedUrl = URL.replaceAll("\\{query\\}", generateEventQuery(keywords));
            System.out.println(String.format("Making request [%s]", preparedUrl));
            return processClassifierResult(ChloeConfig.getInstance().getRestTemplate().getForObject(preparedUrl, String.class));
        }
        
        return Collections.emptySet();
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

    public Set<String> processClassifierResult(String resultString) {
        try {
            ObjectNode jsonObject = ChloeConfig.getInstance().getObjectMapper(resultString);
            JsonNode docs = jsonObject.get("response").get("docs");

            Iterator<JsonNode> it = docs.iterator();
            Set<String> result = new HashSet<>();

            while (it.hasNext()) {
                result.addAll(getItemsFromDoc(it.next()));
            }

            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Collection<String> getItemsFromDoc(JsonNode docs) {
        JsonNode items = docs.get("product");
        Iterator<JsonNode> it = items.iterator();

        Collection list = new ArrayList();
        while (it.hasNext()) {
            list.add(it.next().getTextValue());
        }

        return list;
    }

}
