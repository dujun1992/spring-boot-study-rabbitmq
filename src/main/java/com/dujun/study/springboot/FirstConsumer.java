package com.dujun.study.springboot;

import com.dujun.study.javaapi.IOTest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-9-27 15:57
 */
@Configuration
@RabbitListener(queues = "SIMPLE_QUEUE")
public class FirstConsumer {

    @RabbitHandler
    public void process(Object obj) throws UnsupportedEncodingException {
        Message o = (Message)obj;
        System.out.println(o);
        String msg = new String(o.getBody(), "UTF-8");
        System.out.println("Received message : '" + msg + "'");
        System.out.println("consumerTag : " +  o.getMessageProperties().getConsumerTag());
        System.out.println("deliveryTag : " +  o.getMessageProperties().getDeliveryTag());

        //存储文件
        IOTest.byteArrayToFile(o.getBody(), "C:\\Users\\DuJun\\Desktop\\tmp.log"+o.getMessageProperties().getDeliveryTag());  //字节数组转为图片
    }
}
