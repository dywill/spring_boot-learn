Springboot与缓存
---
+ springboot快速使用缓存
    + 使用流程：
        + 1、在pom中引入springboot缓存相关的启动器
        + 2、在主程序上添加@EnableCaching注解，开启缓存相关功能
        + 3、在需要缓存结果的方法上添加@Cacheable注解（或其他缓存相关注解）即可
---        
+ springboot缓存的重要概念与相关注解        
    + 重要概念：
        + Cache：缓存接口，定义缓存操作，其实现有：RedisCache、EhCacheCache、ConcurrentMapCache等
        + CacheManager：缓存管理器，管理各种缓存（Cache）组件 
    + 相关注解
        + @Cacheable 根据方法的请求参数对缓存结果进行缓存
        + @CacheEvict 清空缓存（例如执行删除操作后，将缓存中的对应数据也同步进行删除）
        + @CachePut 保证方法被调用，但同时缓存方法结果（即对某个对象更新后，返回该对象，需要将该跟新同步到缓存中）  
        --- 详见personService类中的方法，有相关的缓存使用
---
+ springboot缓存工作原理
    + 1、springboot的缓存自动配置类CacheAutoConfiguration通过@Import(CacheConfigurationImportSelector.class)导入了一系列的缓存配置（如下列）
        ```
        org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
        org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
        org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
        org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
        org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
        org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
        org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
        org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
        org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
        org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
        org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
        ```  
    + 2、默认仅注入SimpleCacheConfiguration配置类（依靠@Conditional类似注解选择性注入）
    + 3、该配置类向容器中注册了CacheManager，此处为ConcurrentMapCacheManager（其他的配置类与此类似）
    + 4、缓存的目标方法调用流程
        + 1）优先调用CacheManager接口中的getCache(String name)方法，获取到对应缓存
        + 2）若成功获取到cache组件，且cache中有该缓存则直接返回（不经过目标方法）
        + 3）若缓存为获取到，则调用目标方法（调用keyGenerator生成key保存缓存）
        + 4）返回结果
---
+ springboot搭建redis缓存
    + 1、在开启缓存的情况下，引入springboot的redis相关的启动器
    + 2、此时自动配置中RedisCacheConfiguration会生效，会向容器中注入Redis相关的CacheManager（若需要即可进行自动配置）
    + 3、配置好redis相关的配置即可（host， port， password等）