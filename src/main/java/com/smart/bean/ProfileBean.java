package com.smart.bean;

import groovy.util.logging.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * Created by admin on 2018/8/9.
 */
@Profile("dev")//支持数组:@Profile({"dev","test"})
@Configuration
@Slf4j
public class ProfileBean {
        @PostConstruct
        public void init() {
            System.out.println("dev环境下执行");
        }
}
