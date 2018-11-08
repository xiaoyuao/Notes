package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *@desc
 * SpringBootApplication 是springboot的核心注解，主要用来开启自动配置  此注解是个组合注解，包括了@SpringBootConfiguration、@EnableAutoConfiguration和@ComponentScan注解。
 @SpringBootConfiguration 继承至@Configuration，对于熟悉spring的开发者而言，此标注当前类是配置类，并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中
 ，并且实例名就是方法名。
 @EnableAutoConfiguration 这个注解就是springboot能自动进行配置的魔法所在了。主要是通过此注解，
 能所有符合自动配置条件的bean的定义加载到spring容器中，比如根据spring-boot-starter-web ，
 来判断你的项目是否需要添加了webmvc和tomcat，就会自动的帮你配置web项目中所需要的默认配置。一般上也单独使用不要这个注解，但比如需要排除一些无需自动配置的类时，可利用exclude进行排除。
 @ComponentScan 扫描当前包及其子包下被@Component，@Controller，@Service，@Repository等注解标记的类并纳入到spring容器中进行管理。
 */
@SpringBootApplication
public class APP {
    //main方法作为项目启动的入口，启动springboot项目
    public static void main(String[] args) {
        SpringApplication.run(APP.class, args);
    }
}
