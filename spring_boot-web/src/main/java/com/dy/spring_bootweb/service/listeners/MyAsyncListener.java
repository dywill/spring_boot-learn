package com.dy.spring_bootweb.service.listeners;

import com.dy.spring_bootweb.service.DeferredResultHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 监听器
 *  当spring容器刷新启动时，该监听器会监听到该时间，并调用监听方法
 */
@Component
public class MyAsyncListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private static Logger logger = LoggerFactory.getLogger(MyAsyncListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        logger.warn("-=-=-=-=-=-= listener start on -=-=-=-=-=-=-=-");

        // 使用一个新线程来进行处理
        new Thread(() -> {

            while (true){

                logger.warn("-=-=-=-=-=-= listener start loop -=-=-=-=-=-=-=-");

                if(deferredResultHolder.hasComponent()){

                    logger.warn("-=-=-=-=-=-= listener execute -=-=-=-=-=-=-=-");

                    // 此处即 将 消息队列 中消息取出进行处理
                    DeferredResult<String> result = deferredResultHolder.get();
                    result.setResult("deferred test success");

                }else {

                    logger.warn("-=-=-=-=-=-= listener sleep -=-=-=-=-=-=-=-");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
