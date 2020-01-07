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
            + 步骤一. 在yml配置文件中配置属性及值
            + 步骤二. 编写对应java实体类
            + 步骤三. 在java实体类上添加@ConfigurationProperties注解，并指定prefix属性
        + @Value注解实现属性绑定    
            + 在实体类的对应属性上添加@Value注解