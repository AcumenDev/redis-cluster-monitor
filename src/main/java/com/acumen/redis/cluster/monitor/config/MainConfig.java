package com.acumen.redis.cluster.monitor.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by vladimir akummail@gmail.com on 11/15/16.
 */
@Configuration
public class MainConfig {
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager guavaCacheManager = new GuavaCacheManager("info", "slots","nodes","nodesInfo","nodeInfo");
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.SECONDS));
        return guavaCacheManager;
    }
}