package com.chloe.config;

import java.io.IOException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ChloeConfig {

    private RestTemplate restTemplate;
    private static ChloeConfig chloeConfig;

    private ChloeConfig() {
    }

    public static ChloeConfig getInstance() {
        if (chloeConfig == null) {
            chloeConfig = new ChloeConfig();
        }

        return chloeConfig;
    }

    public RestTemplate getRestTemplate() {

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        }

        return restTemplate;
    }

    public ObjectNode getObjectMapper(String response) throws IOException {
        return new ObjectMapper().readValue(new JsonFactory().createJsonParser(response), ObjectNode.class);
    }
}
