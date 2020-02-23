package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://stackoverflow.com/questions/6390810/implement-a-simple-factory-pattern-with-spring-3-annotations
@Component
public class FraudeFactory {

    private static final Map<Boolean, FraudeService> myServiceCache = new HashMap<>();

    @Autowired
    private ConfigurationRepository repository;

    @Autowired
    private List<FraudeService> fraude;

    @PostConstruct
    public void init() {
        for(FraudeService service : fraude) {
            myServiceCache.put(service.activeWhen(), service);
        }
    }

    public FraudeService getService() throws JsonProcessingException {
        Configuration configuration = repository.findAll().iterator().next();
        Metadata metadata = new ObjectMapper().readValue(configuration.getValue(), Metadata.class);
       return  myServiceCache.get(metadata.isFraudeActive());
    }
}
