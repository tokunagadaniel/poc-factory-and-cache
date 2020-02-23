package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestConfigurationController {

    @Autowired
    private CacheServiceImpl cacheService;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private FraudeFactory fraudeFactory;

    @GetMapping("/run")
    public void run() throws JsonProcessingException {
        fraudeFactory.getService().execute();
    }

    @GetMapping("/get")
    public String get() throws JsonProcessingException {
        return cacheService.getConfiguration().toString();
    }

    @GetMapping("/clear")
    public String clear() {
        cacheService.clearConfiguration();
        return "CLEAR";
    }

    @GetMapping("/put1")
    public String put1() throws JsonProcessingException {
        Metadata metadata = Metadata.builder()
                .fraudeActive(true)
                .contaActive(false)
                .build();

        Configuration config = new Configuration(new ObjectMapper().writeValueAsString(metadata));

        configurationRepository.save(config);

        configurationRepository.findAll().forEach(p -> System.out.println(p.toString()));

        return "OK";
    }

    @GetMapping("/put2")
    public String put2() throws JsonProcessingException {
        Metadata metadata = Metadata.builder()
                .fraudeActive(false)
                .contaActive(false)
                .build();

        Configuration config = new Configuration(new ObjectMapper().writeValueAsString(metadata));

        configurationRepository.deleteAll();
        configurationRepository.save(config);

        configurationRepository.findAll().forEach(p -> System.out.println(p.toString()));

        return "OK";
    }
}
