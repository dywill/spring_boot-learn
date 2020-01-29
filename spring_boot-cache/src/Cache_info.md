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
---
+ springboot缓存工作原理
    + 由CacheAutoConfiguration开始分析
    + CacheAutoConfiguration导入缓存相关配置 @Import(CacheConfigurationImportSelector.class)
    + 导入若干缓存相关配置类，默认SimpleCacheConfiguration该配置类生效
    + 方法运行流程（debug）