package com.alexb.cachingexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableCaching
public class CachingExampleApplication {

    @Autowired
    private CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(CachingExampleApplication.class, args);
    }

    @Bean
    public Map<String, String> map() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("ALEX","DEVELOPER");
        return stringStringHashMap;
    }

    @GetMapping("/delete-all")
    public void deleteAll() {
        cacheService.deleteAll();
    }

    @GetMapping("/")
    public String getJobByName(@RequestParam(name = "name") String name) {
        return cacheService.getByName(name);
    }

    @GetMapping("/put")
    public void save(@RequestParam(name = "name") String name, @RequestParam(name = "job") String job) {
        cacheService.add(name, job);
    }
}
