package com.dujun.study;

import com.dujun.study.javaapi.IOTest;
import com.dujun.study.rcvmq.TadsHksFileProducer;
import com.dujun.study.rcvmq.produceThread;
import com.dujun.study.springboot.MyProducer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RcvMqApplication {
    public static String scanDir = "C:\\Users\\DuJun\\Desktop\\rcvProducer";
    public static String bakDir = "C:\\Users\\DuJun\\Desktop\\rcvProducerBak";



    public static void main(String[] args) {

        ConfigurableApplicationContext ca  = SpringApplication.run(RcvMqApplication.class, args);
        TadsHksFileProducer tadsHksFileProducer = (TadsHksFileProducer) ca.getBean("tadsHksFileProducer");
        //tadsHksFileProducer.send("fanout.exchange.tads_hks",coverMessage("C:\\Users\\DuJun\\Desktop\\rcvProducerBak\\tads_hks2\\S01E01YE011911141258.SMS"));

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "tads_hks2" ,scanDir + File.separator + "tads_hks2" , "fanout.exchange.tads_hks").start();

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "tads_kt2" ,scanDir + File.separator + "tads_kt2" , "fanout.exchange.tads_kt").start();

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "tfds2" ,scanDir + File.separator + "tfds2" , "fanout.exchange.tfds").start();

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "thds_hks2" ,scanDir + File.separator + "thds_hks2" , "fanout.exchange.thds_hks").start();

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "thds_kt2" ,scanDir + File.separator + "thds_kt2" , "fanout.exchange.thds_kt").start();

        new produceThread(tadsHksFileProducer,bakDir + File.separator + "tpds2" ,scanDir + File.separator + "tpds2" , "fanout.exchange.tpds").start();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public  static Message coverMessage(String filePath){
        byte[] body = IOTest.fileToByteArray(filePath);
        Map<String, Object> headers = new HashMap();
        MessageProperties mp = new MessageProperties();
        mp.setHeader("filename", getFileName(filePath));
        return new Message(body,mp);
    }

    public static  String getFileName(String filePath){
        String[] arr = filePath.split("\\\\");
        System.out.println(arr[arr.length-1]);
        return arr[arr.length-1];
    }



}
