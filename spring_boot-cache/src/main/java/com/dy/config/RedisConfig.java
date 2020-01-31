package com.dy.config;

import com.dy.entity.Person;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

import java.lang.reflect.Method;
import java.util.List;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String,Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setDefaultSerializer(serializer);

        return template;
    }

    @Bean
    public RedisTemplate<String, Person> personRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer serializer = new Jackson2JsonRedisSerializer(Person.class);
        template.setDefaultSerializer(serializer);

        return template;
    }

    /**
     * 自定义redis的缓存管理器，并指定其使用的序列化template
     * @param personRedisTemplate
     * @return
     */
    @Bean
    public RedisCacheManager myCacheManager(RedisTemplate<String, Person> personRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(personRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public KeyGenerator uuidKeyGenerator(IdGenerator myIdGenerator){

        return (target, method, params) -> {
            String methodName = method.getName();
            String uuid = myIdGenerator.generateId().toString().replace("-", "");
            return methodName + uuid;
        };
    }
    
    @Bean
    public IdGenerator myIdGenerator(){
        return new SimpleIdGenerator();
    }
}
