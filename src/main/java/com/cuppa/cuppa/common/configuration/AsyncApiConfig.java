package com.cuppa.cuppa.common.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAsyncApi
public class AsyncApiConfig {
    
    private final String amqpHost;
    private final String amqpPort;
    
    public AsyncApiConfig() {
        this.amqpHost = "localhost";
        this.amqpPort = "5672";
    }
    
    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                        .version("0.0.1")
                        .title("Cuppa project")
                        .build();
        
        Server amqp = Server.builder()
                            .protocol("amqp")
                            .url(String.format("%s:%s", amqpHost, amqpPort))
                            .build();
        
        return AsyncApiDocket.builder()
                             .basePackage("com.cuppa.cuppa")
                             .info(info)
                             .server("cuppa-mq", amqp)
                             .build();
    }
}