package com.dy.springboot_data.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    

//    // 若数据源连接池非spring拥有的默认数据源连接池（dbcp hikari tomcat）
//    // 则需要手动配置一个数据源并使用@ConfigurationProperties注解进行属性绑定
//    @Bean
//    public DataSource myDataSource(){
//        HikariDataSource dataSource = new HikariDataSource();
//
//
//        return dataSource;
//    }

    @Bean
    public ConfigurationCustomizer myMybatisCustomize(){
        return new MyMybatisCustomize();
    }


    /**
     * mybatis的自定义器
     *  可以用于自定义mybatis实际使用的一些属性
     */
    static class MyMybatisCustomize implements ConfigurationCustomizer {

        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            configuration.setMapUnderscoreToCamelCase(true);
        }
    }
}
