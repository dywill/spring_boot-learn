SpringBoot与数据库连接
---
+ springboot配置jdbc和其自动配置原理。
    + 配置方法（详见application.yml）
    + 配置原理详见DataSourceConfiguration
    + 使用springboot已有的数据源方法：
        + 1、在application.yml中配置数据库连接相关参数（可通过type属性指定使用数据源的类型）
        + 2、由springboot自动注册数据源后，通过@Autowired注解即可直接注入数据源获取连接进行使用
    + 使用自定义的数据源（例如druid，springboot未对其进行自动注入支持）
        + 1、可向容器中通过@Bean注解创建指定数据源对象（例如druid的数据源）
        + 2、使用@ConfigrationProperties注解绑定appliction.yml中的配置（或在创建bean时直接set）
---
+ Springboot配置mybatis
    + 配置流程：
        + 1、导入mybatis适配springboot的启动器（详见pom.xml）
        + 2、该启动器导入了mybatis的SqlSessionFactory，可通过application.yml或者注入ConfigurationCustomizer对session工厂进行自定义配置
        + 3、通过@Mapper或@MapperScan（指定某个包下都扫描未mapper实现类）的方式指定mapper接口
        + 4、通过配置mapperLocation属性指定mapper.xml文件所在的位置编写mybatis的xml       
        