package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "configuration-cache")
public class CacheServiceImpl {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Cacheable
    public Iterable<Configuration> getConfiguration() {
        return configurationRepository.findAll();
    }

    @CacheEvict
    public void clearConfiguration() {
        System.out.println("Limpando tudo");
    }
}
