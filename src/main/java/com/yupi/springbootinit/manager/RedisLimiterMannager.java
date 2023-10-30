package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

/**
 * @author LVye
 * @version 1.0
 * 专门提供 RedisLimiter 限流基础服务的 （提供了通用的能力）
 */
public class RedisLimiterMannager {
    @Resource
    private RedissonClient redissonClient;

    /**
     * 限流操作
     * @param key 区分不同的限流器 ，比如不同的用户 id 应该分别统计
     */
    public void doRateLimit(String key){
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(key);
        rRateLimiter.trySetRate(RateType.OVERALL,2,1, RateIntervalUnit.SECONDS);

        //每当一个操作来了后，请求一个令牌
        boolean canOp = rRateLimiter.tryAcquire(1);
        //如果没有令牌抛出异常
        if (!canOp){
            throw new BusinessException(ErrorCode.TOO_MAY_REQUEST);
        }
    }
}
