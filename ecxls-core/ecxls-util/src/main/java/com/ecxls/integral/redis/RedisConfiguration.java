package com.ecxls.integral.redis;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>ClassName: RedisConfiguration<p>
 * <p>Description: Redis缓存配置<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年10月26日 下午1:49:36
 */
@Configuration
@EnableCaching
public class RedisConfiguration {
	
	@Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
                    throws UnknownHostException {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        //FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<Object>(Object.class);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
       // redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        //redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
                    throws UnknownHostException {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
    	// 设置缓存的默认过期时间，也是使用Duration设置
    	config = config.entryTtl(Duration.ofMinutes(43200))     
                .disableCachingNullValues();
    	// 使用自定义的缓存配置初始化一个cacheManager
    	RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)     
                .build();
    	//RedisCacheManager.create(redisConnectionFactory);
        return cacheManager;
    }
    
    /**
     * 自定义的缓存key的生成策略
     * 如果想使用这个key  只需要讲注解上keyGenerator的值设置为keyGenerator即可
     * @createTime:2018年10月26日 下午1:48:43
     * @return 自定义策略生成的key
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for(Object obj:params){
                    sb.append(obj.toString());
                }
                return sb.toString();
           }
        };
    }
}
