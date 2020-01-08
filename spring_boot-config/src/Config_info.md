### Spring Boot 应用的配置

+ 使用 Spring Boot 创建向导创建Spring Boot 项目的说明
    + 该向导会自动生成主程序，我们只需要编写业务逻辑即可
    + **resources** 文件夹结构说明
        + static: 保存所有静态资源
        + template: 保存模板页面
        + application.properties: Spring Boot的配置文件

+ 配置文件
    + 介绍：springboot使用一个全局的配置文件，配置文件名固定为application.properties,application.yml
    + 作用：修改springboot的自动配置的默认值

+ YML配置文件
    + yaml
        + 介绍：yaml（Ain`t Markup Language）是/不是一个标记语言，其特点为以数据为中心更适合作为配置文件
        + 语法： 详见配置文件下yaml包及application.yml文件相关介绍
    + SpringBoot中关于yml配置文件值的获取
        + @ConfigurationProperties   注解实现属性绑定
            + 步骤一. 在yml配置文件（或properties文件）中配置属性及值
            + 步骤二. 编写对应java实体类
            + 步骤三. 在java实体类上添加@ConfigurationProperties注解，并指定prefix属性
            + 补充：可以将properties文件中的属性分离到另一个文件中，使用@PropertySource(value = "classpath:cat.properties")注解指定文件路径，但是不支持yml的配置文件
        + @Value注解实现属性绑定    
            + 在实体类的对应属性上添加@Value注解  

+ 自定义的配置文件
    + @ImportResource，通过此注解标注在@Configuration注解上导入spring的xml配置文件（不推荐）
    + 通过@Configuration注解，使用注解版的spring向容器中添加组件
    
+ 配置文件中值的占位符写法
    + 配置文件中可以使用${app.name}的形式，引用其他配置（详见application.yml）
+ profile功能
    + 介绍：可以解决项目在不同环境下使用不同配置文件的配置文件切换需求
    + 使用：
        + 1 创建application-{xxx}.yml (properties配置文件同理)
        + 2 在主配置文件中指定spring.profile.active=xxx属性，即会激活对用的配置文件
 + 总结
    + 本篇章大致讲解springboot配置文件放置的位置，写法以及使用，配置文件能放置的地方有很多，可以指定配置的地方也很多，例如有命令行参数，java虚拟机参数，项目路径下，类路径下，项目同路径下config下等，springboot对此的逻辑为从优先级高的向优先级低的依次读取，然后由高优先级的配置来覆盖低优先级的配置，最终形成一个新的互补配置    