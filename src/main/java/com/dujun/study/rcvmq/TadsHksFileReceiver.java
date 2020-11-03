package com.dujun.study.rcvmq;

import com.dujun.study.javaapi.IOTest;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-11-2 15:02
 */
@Component
//@RabbitListener(queues = "tads_hks2")
public class TadsHksFileReceiver {

    //@RabbitHandler
    public void processMessage2(byte[] data, Channel channel, Message message) {

        String filename = "C:\\Users\\DuJun\\Desktop" + File.separator + message.getMessageProperties().getHeaders().get("filename").toString();
        try {
            System.out.println("开始接收文件,DeliveryTag:"+message.getMessageProperties().getDeliveryTag() +",filename:" + filename);


            IOTest.byteArrayToFile(data, filename);   // 写入文件

            // 告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);


            System.out.println("接收文件成功：" + filename);

        } catch (Exception e) {
            e.printStackTrace();
            // 丢弃这条消息
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("接收文件失败：" + filename);
        }
    }
}
