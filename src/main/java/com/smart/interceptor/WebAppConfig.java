package com.smart.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by admin on 2018/8/9.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
    }
}
