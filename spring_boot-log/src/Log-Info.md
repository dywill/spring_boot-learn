### 日志框架介绍
+ 日志门面（日志抽象层）/ 日志实现

    |日志门面（日志抽象层）|日志实现|
    |:---|:---:|
    |JCL(Jarkarta Commons Logging)  SLF4j(Simple Loggin Facade for java)  jboss-logging  | log4j  JUL(java.util.logging)  log4j2  logback |

+ 抽象层介绍
    + 1 jboss-logging 使用场景太少
    + 2 JCL(Jarkarta Commons Logging) 已很久未更新
    + 结论： 使用 slf4j作为抽象层

+ 实现层介绍
    + 1 log4j slf4j logback 出自同一个人之手适配性会更好，其中logback为log4j的改进版
    + 2 JUL是java未避免日志市场全部被占领，而勉强出的一个版本（故可能设计上不如logback）
    + 3 log4j2为apache借log4j的名而开发的一个日志框架（比较新，暂时还未被其他框架适配起来）

+ SpringBoot的日志框架使用
    + springboot的底层spring默认使用的是JCL，而springboot使用的是slf4j + logback
    + 调用日志应当统一使用Slf4j（即抽象层）调用打印日志。而日志的配置文件则应当使用日志实现层的日志打印
    + SpringBoot日志的实际使用
        + 可以在application.yml全局配置文件中设置一些日志相关的属性 --- 详见application.yml
        + 想要自己使用日志配置xml来覆盖默认的配置，可以在类路径下配置logback.xml 或 logback-spring.xml  
        ------ 建议使用logback-spring.xml，会被spring处理后在交给日志框架解析，能够使用一些spring的高级功能  
        ------ 详见该项目的logback-spring.xml文件  
        ------ springboot的默认配置可以在其包下的logging文件夹中查找
        
    
    