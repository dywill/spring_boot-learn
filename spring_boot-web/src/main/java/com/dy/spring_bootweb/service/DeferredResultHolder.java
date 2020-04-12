package com.dy.spring_bootweb.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> deferredResultMap = new HashMap<>();

    public boolean put(String str, DeferredResult<String> deferredResult){
        this.deferredResultMap.put(str,deferredResult);
        return true;
    }

    public DeferredResult<String> get(){
        Set<String> keySet = this.deferredResultMap.keySet();
        for (String key : keySet) {
            DeferredResult<String> deferredResult = deferredResultMap.remove(key);
            return deferredResult;
        }
        return null;
    }

    public boolean clear(){
        deferredResultMap.clear();
        return true;
    }

    public boolean hasComponent(){
        return !this.deferredResultMap.isEmpty();
    }
}
