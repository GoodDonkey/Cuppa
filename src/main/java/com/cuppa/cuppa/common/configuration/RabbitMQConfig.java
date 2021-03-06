package com.cuppa.cuppa.common.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    
    private static final String CHAT_QUEUE_NAME = "chat.queue";
    private static final String CHAT_QUEUE_NAME_2 = "chat-queue";
    private static final String CHAT_EXCHANGE_NAME = "chat.exchange";
    private static final String ROUTING_KEY = "to.*";
    
    @Value("${infra.rabbitmq.host}")
    private String RABBITMQ_HOST;
    
    @Value("${infra.rabbitmq.username}")
    private String RABBITMQ_USERNAME;
    
    @Value("${infra.rabbitmq.password}")
    private String RABBITMQ_PASSWORD;
    
    @Bean
    public Queue queue(){ return new Queue(CHAT_QUEUE_NAME, true); }
    
    @Bean
    public Queue queue2(){ return new Queue(CHAT_QUEUE_NAME_2, false); }
    
    @Bean
    public TopicExchange exchange(){ return new TopicExchange(CHAT_EXCHANGE_NAME); }
    
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    
    // messageConverter ??? ?????????????????? ?????? ?????? RabbitTemplate Bean ??? ?????? ??????
    // ConnectionFactory, MessageConverter ?????? ???????????? ???????????? ??????.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter jsonMessageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        rabbitTemplate.setRoutingKey(ROUTING_KEY);
        return rabbitTemplate;
    }
    
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(CHAT_QUEUE_NAME);
        return container;
    }
    
    // Spring ?????? ????????????????????? ConnectionFactory ??? SimpleConnectionFactory ??????
    // CachingConnectionFactory ??? ?????? ????????????
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);
        return factory;
    }
    
    @Bean
    public MessageConverter jsonMessageConverter(){
        // Todo: ?????? ????????? ?????? ????????? ???????????? (???????????????)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.registerModule(new JavaTimeModule());
    
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
