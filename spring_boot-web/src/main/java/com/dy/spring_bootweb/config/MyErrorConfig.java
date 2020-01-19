package com.dy.spring_bootweb.config;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.util.NestedServletException;

import java.util.Map;

@Configuration
public class MyErrorConfig {


    @Bean
    public ErrorAttributes myErrorAttributes(){
        return new MyErrorAttributes();
    }

    private static final String CODE = "javax.servlet.error.status_code";
    private static final String URI = "javax.servlet.error.request_uri";
    private static final String EXCEPTION = "javax.servlet.error.exception";


    private static class MyErrorAttributes extends DefaultErrorAttributes{

        @Override
        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
            Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);

            Integer code = (Integer) requestAttributes.getAttribute(CODE, RequestAttributes.SCOPE_REQUEST);
            String uri = (String) requestAttributes.getAttribute(URI, RequestAttributes.SCOPE_REQUEST);
            NestedServletException exception = (NestedServletException) requestAttributes.getAttribute(EXCEPTION, RequestAttributes.SCOPE_REQUEST);

            return map;
        }
    }
}
