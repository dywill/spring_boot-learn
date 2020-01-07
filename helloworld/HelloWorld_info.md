### Hello World 项目探究
+ pom文件
    + 父项目
        ```    
        项目引入的spring-boot:
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>1.5.9.RELEASE</version>
        </parent>
        
        他的父项目是
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>1.5.9.RELEASE</version>
            <relativePath>../../spring-boot-dependencies</relativePath>
        </parent>
        他来真正管理Spring-boot应用里的所有依赖版本，可以认为是Spring的版本仲裁中心
        ```
    + 导入的依赖
        ```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        ```
        **spring-boot-starter**-web  
            sping-boot-starter: spring-boot场景启动器，帮我们导入了web模块正常运行时所依赖的组件  
        **spring boot 将所有的功能场景抽取出来，作成一个个的starters（启动器），只需要引入对应启动器，则会在项目中引入对应的依赖**  
---
+ 主程序类
    + @SpringBootApplication：用于说明该类为SpringBoot的主配置类，SpringBoot应该运行该类的main方法来启动SpringBoot应用
        ```$xslt
            @Target({ElementType.TYPE})
            @Retention(RetentionPolicy.RUNTIME)
            @Documented
            @Inherited
            @SpringBootConfiguration
            @EnableAutoConfiguration
            @ComponentScan(
                excludeFilters = {@Filter(
                type = FilterType.CUSTOM,
                classes = {TypeExcludeFilter.class}
            ), @Filter(
                type = FilterType.CUSTOM,
                classes = {AutoConfigurationExcludeFilter.class}
            )}
            )
            public @interface SpringBootApplication {
            ...
        ```
       1. @SpringBootConfiguration: Spring Boot的配置类  
        标志在某个类上，表示这是一个Spring Boot的配置类，配置类也是容器内的一个组件  
       2. @EnableAutoConfiguration: 开启自动配置功能  
        ```$xslt
            @Target({ElementType.TYPE})
            @Retention(RetentionPolicy.RUNTIME)
            @Documented
            @Inherited
            @AutoConfigurationPackage
            @Import({EnableAutoConfigurationImportSelector.class})
            public @interface EnableAutoConfiguration {
            ... 也是一个组合注解
            
            @AutoConfigurationPackage: 自动配置包
                组合注解:@Import(AutoConfigurationPackages.Registrar.class) 给容器中导入了一个组件
                其作用为将主配置类的所在包以及所在包下的所有子包下的所有Spring组件扫描加入到Spring容器之中
                  
            @Import({EnableAutoConfigurationImportSelector.class}): 导入组件的选择器
                会给容器中导入xxxAutoConfiguration（自动配置类），该自动配置类即避免我们手动编写大量默认配置    
        ``` 
        
       
        