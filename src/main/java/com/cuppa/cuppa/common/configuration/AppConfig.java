package com.cuppa.cuppa.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:env.${spring.profiles.active}.properties")
@Slf4j
public class AppConfig {
}
