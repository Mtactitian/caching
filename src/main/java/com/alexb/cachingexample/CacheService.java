package com.alexb.cachingexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CacheService {

    @Autowired
    private Map<String, String> cache;

    public void add(String name, String job) {
        cache.put(name, job);
    }

    @Cacheable("job-cache")
    public String getByName(String name) {
        log.info("returned by name");
        return cache.getOrDefault(name, "Unknown");
    }

    public void deleteAll() {
        cache.clear();
    }
}
