package ru.finnetrolle.xdgs.storage.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by finnetrolle on 14.02.2016.
 */
@Configuration
@EnableRabbit
public class RabbitMqConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqConfiguration.class);

    @Value("${rabbit.hostname}") private String hostname;
    @Value("${rabbit.username}") private String username;
    @Value("${rabbit.password}") private String password;

    @Value("${rabbit.queue.auth.signin}") private String signInQueue;
    @Value("${rabbit.queue.auth.signup}") private String signUpQueue;

    @Bean
    public List<Declarable> configureQueueNetwork() {
        log.debug("Configuring queues");
        return Arrays.asList(
                new Queue(signInQueue, true, false, false),
                new Queue(signUpQueue, true, false, false)
        );
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        log.debug("Configuring connection factory");
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(hostname);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        log.debug("Configuring admin");
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        return admin;
    }

    @Bean
    public MessageConverter messageConverter(){
        log.debug("Configuring message converter");
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        log.debug("Configuring template");
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        log.debug("Configuring listener factory");
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(messageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(2);
        return factory;
    }



}
