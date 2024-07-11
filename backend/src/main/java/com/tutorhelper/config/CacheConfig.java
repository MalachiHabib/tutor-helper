package com.tutorhelper.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    private static final List<String> CACHE_NAMES = Arrays.asList("students", "allStudents", "tutors", "allTutors");
    private static final int CACHE_EXPIRE_AFTER_WRITE_MINUTES = 15;
    private static final int CACHE_MAXIMUM_SIZE = 100;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(CACHE_NAMES);
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
            .expireAfterWrite(CACHE_EXPIRE_AFTER_WRITE_MINUTES, TimeUnit.MINUTES)
            .maximumSize(CACHE_MAXIMUM_SIZE);
    }
}
