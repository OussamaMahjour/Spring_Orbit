package com.faceboot.post_service.conf;

import com.netflix.spectator.impl.PatternExpr;
import feign.form.spring.SpringFormEncoder;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import feign.codec.Encoder;


@Configuration
public class FeignMultipartConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter())));
    }
}