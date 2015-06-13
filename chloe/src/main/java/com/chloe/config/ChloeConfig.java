package com.chloe.config;

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

}
