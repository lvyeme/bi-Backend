package com.yupi.springbootinit.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LVye
 * @version 1.0
 */
@ConfigurationProperties(prefix = "spring.redis")
@Data
@Configuration
public class RedissonConfig {
    private Integer database;
    private String host;
    private Integer port;
    private String password;
    // Sync and Async API
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(database)
                .setAddress("redis//" + host + ":" + port);
//                .setPassword(password);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
