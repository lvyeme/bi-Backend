package com.yupi.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author LVye
 * @version 1.0
 */
@SpringBootTest
class RedisLimiterManagerTest {

    @Resource
    private RedisLimiterMannager redisLimiterMannager;
    @Test
    void doRateLimit() throws InterruptedException {
        String userId = "1";
        for (int i = 0; i < 2; i++){
            redisLimiterMannager.doRateLimit(userId);
            System.out.println("成功");
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++){
            redisLimiterMannager.doRateLimit(userId);
            System.out.println("成功");
        }
    }
}