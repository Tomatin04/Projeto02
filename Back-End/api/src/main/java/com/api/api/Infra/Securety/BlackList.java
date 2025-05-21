package com.api.api.Infra.Securety;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class BlackList {

    @Autowired
    private StringRedisTemplate redis;

    private long HOURES = 108000;

    public void blacklistToken(String token) {
        redis.opsForValue().set(token, "blacklisted", HOURES);
    }

    public boolean isTokenBlacklisted(String token) {
        return redis.hasKey(token);
    }
}
