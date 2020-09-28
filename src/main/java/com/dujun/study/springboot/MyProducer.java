package com.dujun.study.springboot;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-9-27 16:00
 */
@Component
public class MyProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public  void send(Object obj){
        rabbitTemplate.convertAndSend("SIMPLE_EXCHANGE","wuzz.test",obj);

    }
}
