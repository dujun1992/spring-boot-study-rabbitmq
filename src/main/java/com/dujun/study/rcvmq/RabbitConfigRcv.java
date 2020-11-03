package com.dujun.study.rcvmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-11-2 14:57
 */
@Configuration
public class RabbitConfigRcv {
    @Bean
    public Queue QueueTadsHks2() {
        return new Queue("tads_hks2");
    }

    @Bean
    public Queue QueueTadsKt2() {
        return new Queue("tads_kt2");
    }

    @Bean
    public Queue QueueTfds2() {
        return new Queue("tfds2");
    }

    @Bean
    public Queue QueueThdsHks2() {
        return new Queue("thds_hks2");
    }

    @Bean
    public Queue QueueThdsKt2() {
        return new Queue("thds_kt2");
    }

    @Bean
    public Queue QueueTpds2() {
        return new Queue("tpds2");
    }

    @Bean
    public FanoutExchange fanoutExchangeTadsHks() {
        return new FanoutExchange("fanout.exchange.tads_hks");
    }

    @Bean
    public FanoutExchange fanoutExchangeTadsKt() {
        return new FanoutExchange("fanout.exchange.tads_kt");
    }

    @Bean
    public FanoutExchange fanoutExchangeTfds() {
        return new FanoutExchange("fanout.exchange.tfds");
    }

    @Bean
    public FanoutExchange fanoutExchangeThdsHks() {
        return new FanoutExchange("fanout.exchange.thds_hks");
    }

    @Bean
    public FanoutExchange fanoutExchangeThdsKt() {
        return new FanoutExchange("fanout.exchange.thds_kt");
    }

    @Bean
    public FanoutExchange fanoutExchangeTpds() {
        return new FanoutExchange("fanout.exchange.tpds");
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(QueueTadsHks2()).to(fanoutExchangeTadsHks());
    }

    @Bean
    public Binding fanoutBinding4() {
        return BindingBuilder.bind(QueueTadsKt2()).to(fanoutExchangeTadsKt());
    }

    @Bean
    public Binding fanoutBinding6() {
        return BindingBuilder.bind(QueueTfds2()).to(fanoutExchangeTfds());
    }

    @Bean
    public Binding fanoutBinding8() {
        return BindingBuilder.bind(QueueThdsHks2()).to(fanoutExchangeThdsHks());
    }

    @Bean
    public Binding fanoutBinding10() {
        return BindingBuilder.bind(QueueThdsKt2()).to(fanoutExchangeThdsKt());
    }

    @Bean
    public Binding fanoutBinding12() {
        return BindingBuilder.bind(QueueTpds2()).to(fanoutExchangeTpds());
    }
}
