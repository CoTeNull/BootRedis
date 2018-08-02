package cn.cote.myutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperator {

    @Autowired
    private StringRedisTemplate redisTemplate;

     public void set(String key,String value){
       redisTemplate.opsForValue().set(key,value);
    }
    public String get(String key){
       return redisTemplate.opsForValue().get(key);
    }
}
