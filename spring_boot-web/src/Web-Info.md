### SpringBoot的Web开发
---
+ SpringBoot的静态资源映射规则  
    
    ```
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!this.resourceProperties.isAddMappings()) {
            logger.debug("Default resource handling disabled");
            return;
        }
        Integer cachePeriod = this.resourceProperties.getCachePeriod();
        if (!registry.hasMappingForPattern("/webjars/**")) {
            customizeResourceHandlerRegistration(
                    registry.addResourceHandler("/webjars/**")
                            .addResourceLocations(
                                    "classpath:/META-INF/resources/webjars/")
                    .setCachePeriod(cachePeriod));
        }
        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
        if (!registry.hasMappingForPattern(staticPathPattern)) {
            customizeResourceHandlerRegistration( 
                    registry.addResourceHandler(staticPathPattern)
                            .addResourceLocations(
                                    this.resourceProperties.getStaticLocations())
                    .setCachePeriod(cachePeriod));
        }
    }
  
    ``` 
    + 1 如同上方代码所示/webjars/**请求会到classpath:/META-INF/resources/webjars/目录下寻找资源  
    --- webjar: 以jar包的形式引入jquery之类的文件
    + 2 引入自定义的静态文件，由WebMvcAutoConfiguration的自动配置可知：  
    "classpath:/META-INF/resources/", "classpath:/resources/","classpath:/static/", "classpath:/public/"为静态资源访问存放为文件夹
---    
+ SpringBoot使用模板引擎
    + 引入SpringBoot的Thymeleaf相关的starter，以及如何修改其导入版本  
    --- 此处详见pom文件中的修改
    + 查看如何配置thymeleaf，即thymeleaf的默认配置  
    --- 找到对应自动配置类 ThymeleafAutoConfiguration, 找到其视图解析器配置，发现其默认去classpath下的template目录寻找html文件，故按照其默认新建页面后，与jsp相同方法使用即可
---
+ SpringBoot对SpringMVC的自动配置 
    + SpringMVC的自动配置原理以及一些简单修改ErrorMvaAutoConfiguration
        + SpringMVC对应的自动配置类为WebMvcAutoConfiguration， 其中配置了ContentNegotiatingViewResolver，作用为整合所有视图解析器按顺序获取视图  
        --- ContentNegotiatingViewResolver中的试图解析器从容器中获取，故若需要扩展视图解析器，则在容器中自定义一个视图解析器即可
        + 类似的Converter和Formatter等组件同样直接加入容器中即可
        + 自动注册了MessageCodesResolver 定义了错误代码生成规则
        + 自动注册了ConfigurableWebBindingInitializer 作用为初始化WebBinder用于参数的属性绑定
    + 扩展SpringMVC
        + 可以编写WebMvcConfigurerAdapter的实现类对SpringMVC进行一些扩展（可保留默认配置）  
        --- 原理 使用可以参照 MyMVCConfig 类中演示笔记
        + 全面接管MVC，只需要添加@EnableWebMVC便可以全面接管SpringMVC，即此时SpringBoot的默认配置会全部失效  
        --- 使用时应注意xxxConfigurer类，该类可以对该类型组件进行一些设置
---        
+ SpringBoot的错误处理机制
    + 原理：可以参照 ErrorMvcAutoConfiguration 的错误自动配置
        + 1、DefaultErrorAttributes
        + 2、BasicErrorController
        + 3、ErrorPageCustomizer
        + 4、DefaultErrorViewResolver
    + 运行流程：  
    1、一旦系统出现4xx或5xx之类的错误，ErrorPageCustomizer 就会生效，根据 ErrorProperties 中的默认配置，一旦发生错误，请求就会被发到/error请求去  
    2、其中 BasicErrorController （默认系统中没有即会注入） 这个controller就会处理到这个请求
        + 1）html格式返回数据
            + 其中会视图解析器由默认的 DefaultErrorViewResolver 来解析 /error 请求，生成视图对象来进行对应的响应
            + 由源码可知，有模板引擎的情况下 DefaultErrorViewResolver 默认会去模板引擎下的error文件夹下的 状态码.html 文件
            + 无模板引擎时，则会在寻找所有的静态文件下的error.html页面， 返回第一个找的页面  
            ---还可以使用 4xx 5xx 来命名错误页面来批量处理错误
            + 解析错误视图时，生成的model由 ErrorAttributes 接口下的 DefaultErrorAttributes实现类中的getErrorAttributes方法封装生成mode
        + 2）json格式返回数据
            + 使用方法： 详见controllers.ex包下的异常处理controller类
            + 运行流程：
                + ①BasicErrorController处理该error请求，会使用返回json数据的方法
                + ②调用该controller中的ErrorAttributes的getErrorAttributes方法，获取错误信息（map）  
                --- 故需要自定义返沪参数可以继承DefaultErrorAttributes修改getErrorAttributes方法方法
                + ③调用getStatus方法，向request的予属性中置入对用的http错误状态码
---                
+ SpringBoot的嵌入式servlet容器
    + 嵌入式servlet容器的配置修改
        + 修改servlet容器的配置
            + 1、在主配置文件application.yml中对ServerProperties的属性进行修改（例如此处在yml文件中将服务器端口修改8000）
            + 2、编写自定义的EmbeddedServletContainerCustomizer重写customize方法，来对servlet容器进行自定义配置   
            --- 在Springboot中还会有很多xxxCustomizer，来对相应的模块进行自定义设置
            + 3、注册servlet容器中的三大组件
                + 方式一、通过注册ServletRegistrationBean，来向servlet容器注册servlet（listener、filter可以用相同的方式进行注册）
                + 方式二、通过对应注解直接向servlet容器中进行注册组件
                    + 1）在容器启动类上添加扫描添加web组件的注解@ServletComponentScan
                    + 2）通过servlet中对应的@WebListener注解即可直接向servlet容器中添加对应组件
    + 切换嵌入式Servlet容器（tomcat，jetty，undertow）
        + 切换方法（详见pom.xml）
            + 1、去除原先web场景启动器中默认的tomcat的starter
            + 2、导入新的对应启动器（jetty、undertow）
    + 自动配置Servlet容器 配置原理 启动原理
        + 嵌入式servlet容器的自动配置原理
            + EmbeddedServletContainerAutoConfiguration 嵌入式servlet容器自动配置
                + 会通过@ConditionalOnClass注解向容器中注入tomcat、jetty、undertow的对应对应容器工厂
                + 容器工厂的作用为生产一个对应的嵌入式的servlet容器
                + 容器创建过程时，首先初始化tomcat/jetty/undertow的各个组件，配置好后在创建完成容器后，并直接启动web服务
            + 我们的自定义的配置如何生效
                + 通过application.yml配置文件修改
                    + 该类型的配置和ServerProperties绑定，并且该Properties本身为一个servlet容器的定制器（即会通过bean后处理器来进行调用）
                + 通过EmbeddedServletContainerCustomizer（定制器）实现类对容器进行自定义
                    + EmbeddedServletContainerAutoConfiguration 嵌入式servlet容器自动配置类通过@Import(BeanPostProcessorsRegistrar.class)注解向容器中注入了EmbeddedServletContainerCustomizerBeanPostProcessor（servlet容器定制器的后置处理器）
                    + 此servlet容器定制器后置处理器的作用为在servlet容器初始化后，获取spring容器中的所有servlet容器定制器的customizer方法，对servlet容器进行响应的定制  
                    --- 故多个servlet容器定制器同时生效，最后一个执行的定制器会覆盖先前的（故不应该这样做）
        + 嵌入式servlet容器的启动原理
            + 1、主启动类中运行run方法
            + 2、springboot创建ioc容器，判断当前若为一个web应用，则创建一个对应的web的ioc的容器
            + 3、刷新ioc容器，并在onRefresh方法中，由对应的web应用实现的ico容器子类中重写，创建一个嵌入式的servlet容器
                1. 第一步： 获取嵌入式的servlet容器工厂
                2. 第二步：从ioc容器到servlet容器工厂并进行servlet容器的创建
                3. 第三步：此处即触发了自动配置的流程，对该servlet容器进行自动配置
                4. 第四步：嵌入式的servlet创建完成后，即启动servlet容器（即在调用构造器时，直接启动servlet容器）
---
+ springboot使用外置的servlet容器
    + 简介：即将该springboot应用打包为war包，部署到对应的外置的servlet容器中（即tomcat等）
    + 操作步骤：
        1. 第一步：正常通过spring initializer创建springboot项目，并设置打包方式为war
        2. 第二步：创建web应用需要的对应文件夹和文件（web.xml和WEB-INF等）
        3. 第三步：将嵌入式的tomcat的启动器的maven依赖属性指定为provided
        4. 第四步：编写一个SpringBootInitializer的子类，并调用configure方法传入springboot的主程序
    + 原理：    
                
        