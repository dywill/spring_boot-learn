package com.dy.spring_bootweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过 继承WebMvcConfigurerAdapter 的方式来扩展SpringBoot对mvc的一些默认配置
 *
 * 扩展原理：
 *  1. WebMvcAutoConfiguration 自动配置类中配置了一个WebMvcConfigurerAdapter
 *      @Configuration
 *      @Import(EnableWebMvcConfiguration.class)
 *      @EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
 * 	    public static class WebMvcAutoConfigurationAdapter extends WebMvcConfigurerAdapter {
 * 	        ...
 * 	    }
 * 	   该配置类导入 @Import(EnableWebMvcConfiguration.class) 组件
 *      @Configuration
 * 	    public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
 * 	        ...
 * 	    }
 * 	    该配置类的父类 DelegatingWebMvcConfiguration 的作用即为将所有 WebMvcConfigurerAdapter 的实现类的所有配置方法全部执行一遍
 * 	    例如：
 *      @Override
 * 	    public void addInterceptors(InterceptorRegistry registry) {
 * 	    	for (WebMvcConfigurer delegate : this.delegates) {
 * 	    		delegate.addInterceptors(registry);
 *          }
 * 	    }
 *
 */
@Slf4j

/**
 * @EnableWebMvc
 * 该注解会使所有MVC的默认配置全部失效
 *
 * 原理：
 *  1. 该注解导入了 @Import(DelegatingWebMvcConfiguration.class)， 其父类为 WebMvcConfigurationSupport
 *  2. WebMvcAutoConfiguration自动配置类在有WebMvcConfigurationSupport类时，会失效， @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 */
@EnableWebMvc
@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {

    /**
     * 此处给容器中扩展一个拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 该类的使用为链式调用，先指定拦截器，后指定拦截的路径和不拦截的路径等
        registry.addInterceptor(new TestHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/test");
    }

    private static class TestHandlerInterceptor implements HandlerInterceptor{

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            log.info(" =================> preHandle <==================== ");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        }
    }
}
