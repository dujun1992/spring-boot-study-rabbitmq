package com.dujun.study.springboot;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-9-27 15:45
 */
@Configuration
public class RabbitConfig {
    @Bean
    public DirectExchange simpleExchange(){
        return new DirectExchange("SIMPLE_EXCHANGE");
    }

//    @Bean
//    public FanoutExchange fanoutExchangeTadsHks() {
//        return new FanoutExchange("fanout.exchange.tads_hks");
//    }

    @Bean
    public Queue simpleQueue(){
        return new Queue("SIMPLE_QUEUE");
    }

    @Bean
    public Binding bindFirst(@Qualifier("simpleQueue") Queue queue,
                             @Qualifier("simpleExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("wuzz.test");
    }

//    @Bean
//    public Binding fanoutBinding1() {
//        return BindingBuilder.bind(simpleQueue()).to(fanoutExchangeTadsHks());
//    }
}
