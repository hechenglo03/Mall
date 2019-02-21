package com.hcl.Mall.config;

import ch.qos.logback.classic.pattern.DateConverter;
import com.hcl.Mall.listener.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private Interceptor interceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(this.interceptor)
                .addPathPatterns("/balance/**")
                .addPathPatterns("/product/editor")
                .addPathPatterns("/product/deltete")
                .addPathPatterns("/editor/**");
    }

}

