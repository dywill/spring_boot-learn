### SpringBoot自动配置原理
+ 1 SpringBoot启动时加载了@SpringBootApplication注解，该复合注解其中有一个@EnableAutoConfiguration注解开启了自动配置功能
+ 2 @EnableAutoConfiguration注解的作用
    + 1 该注解的作用主要为通过@Import(EnableAutoConfigurationImportSelector.class)向容器中导入组件  
    （ @Import导入组件回顾：ImportSelector接口的实现类（导入组件为接口返回的string数组）导入ImportBeanDefinitionRegistrar接口的实现类，直接向ioc容器种注册组件）    
    + 2 EnableAutoConfigurationImportSelector.class类为ImportSelector接口的实现类，通过selectImports方法导入组件
    + 3 EnableAutoConfigurationImportSelector的父类AutoConfigurationImportSelector实现了selectImports方法，追溯源码，发现返回String数组来源于类路径下META-INF/spring.factories目录下的EnableAutoConfiguration属性，以下即为导入的自动配置类
    
  
  ```
        org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
        org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
        org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
        org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
        org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
        org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
        org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
        org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
        org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
        org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
        org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
        org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
        org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
        org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
        org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
        org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
        org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
        org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
        org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
        org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
        org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
        org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
        org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
        org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
        org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
        org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
        org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
        org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
        org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
        org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
        org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
        org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
        org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
        org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
        org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
        org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
        org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
        org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
        org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
        org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
        org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
        org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
        org.springframework.boot.autoconfigure.mobile.DeviceResolverAutoConfiguration,\
        org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration,\
        org.springframework.boot.autoconfigure.mobile.SitePreferenceAutoConfiguration,\
        org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
        org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
        org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
        org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration,\
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration,\
        org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration,\
        org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration,\
        org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration,\
        org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
        org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
        org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration,\
        org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration,\
        org.springframework.boot.autoconfigure.social.LinkedInAutoConfiguration,\
        org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration,\
        org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
        org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
        org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
        org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
        org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration,\
        org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration,\
        org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration,\
        org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration,\
        org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
   ```

+ 3 自动配置类的作用
    + 介绍： 每一个xxxAutoConfiguration类，都是一个配置类组件，容器使用该自动配置类来做对应的自动配置
    + 以HttpEncodingAutoConfiguration自动配置类进行示例
        ```java
          @Configuration      //代表该类为配置类，相当于application.xml配置文件
          @EnableConfigurationProperties(HttpEncodingProperties.class)    // 导入properties实例（该实例已和主配置文件application.yml中的对应属性相映射
          @ConditionalOnWebApplication
          @ConditionalOnClass(CharacterEncodingFilter.class)
          @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)   // 以上三个皆为condition注解的引申注解，确定该自动配置类是否注入到容器中（即用于控制自动配置是否生效）
          public class HttpEncodingAutoConfiguration {
            
            // 向容器中注入bean，使用到了properties实体类中的属性来进行配置  
            @Bean
            @ConditionalOnMissingBean(CharacterEncodingFilter.class)
            public CharacterEncodingFilter characterEncodingFilter() {
                CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
                filter.setEncoding(this.properties.getCharset().name());
                filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
                filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
                return filter;
            }
              
            // code ...  
          }
          @ConfigurationProperties(prefix = "spring.http.encoding")   // 该properties实体类已和yml进行了映射
          public class HttpEncodingProperties {
              // 一些映射的属性
          }
        ``` 
+ 总结
    + xxxAutoConfiguration类 相当于 application.xml配置文件，且可以根据环境判断是否需要注入
    + xxxProperties类 与 xxxAutoConfiguration类 成对出现，用于和application.yml配置文件的属性相映射，给开发者一个覆盖默认配置值的入口