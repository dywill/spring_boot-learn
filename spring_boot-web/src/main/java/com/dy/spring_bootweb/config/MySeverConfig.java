package com.dy.spring_bootweb.config;

import com.dy.spring_bootweb.web.filter.MyFilter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MySeverConfig {


    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/aaa","/bbb"));

        return filterRegistrationBean;
    }


    @Bean
    public EmbeddedServletContainerCustomizer myTomcatCustomizer(){
        return new MyEmbeddedServletContainerCustomizer();
    }

    private static class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer{

        // 自定义设置容器的根路径为/web
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setContextPath("/web");
        }

    }
}
