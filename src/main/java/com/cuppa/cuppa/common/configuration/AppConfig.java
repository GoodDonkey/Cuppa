package com.cuppa.cuppa.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Configuration
@PropertySource(value = "classpath:env.${spring.profiles.active}.properties")
@Slf4j
public class AppConfig {
    
    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("현재 시간 = {}", new Date());
    }
}
