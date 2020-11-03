package com.dujun.study.rcvmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-9-27 16:00
 */
@Component
public class TadsHksFileProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public  void send(String exchangeName,Object obj){
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        rabbitTemplate.convertAndSend(exchangeName,"",obj,correlationData);
        rabbitTemplate.setConfirmCallback(confirmCallback);
    }

    /**
     * 配置 confirm 机制
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         *
         * @param correlationData 消息相关的数据，一般用于获取 唯一标识 id
         * @param b 是否发送成功
         * @param error 失败原因
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String error) {
            if (b) {
                System.out.println("confirm 消息已发送到交换机...消息ID为：" + correlationData.getId());
            } else {
                System.out.println("confirm 消息未发送到交换机...消息ID为：" + correlationData.getId() + " 失败原因: " + error);
            }
        }
    };
}
