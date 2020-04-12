package com.dy.spring_bootweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger简介
 *  （以下为与spring-boot结合使用）
 *  1.导入swagger相关的包（见pom）
 *  2.写一个配置类，添加@EnableSwagger2注解即可（也可以做一些简单配置）
 *
 *  --------------------------------------------------------------
 *
 *  注解具体使用可以参照SwaggerController（详细查找文档）
 *
 *  todo
 *  补充：wireMock 伪造一个rest服务
 *
 */
@EnableSwagger2
@Configuration
public class MySwagger2Config {

    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.dy.spring_bootweb.controllers"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("swagger2-dy-测试-api接口文档")
                // 设置联系人
                .contact(new Contact("dy","httP://locahost","haha@163.com"))
                // 描述
                .description("欢迎访问短视频接口文档，这里是描述信息")
                // 定义版本号
                .version("1.0").build();
    }
}
