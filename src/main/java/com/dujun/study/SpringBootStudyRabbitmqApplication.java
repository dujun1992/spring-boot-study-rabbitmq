package com.dujun.study;

import com.dujun.study.javaapi.IOTest;
import com.dujun.study.springboot.MyProducer;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootStudyRabbitmqApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext ca  = SpringApplication.run(SpringBootStudyRabbitmqApplication.class, args);
        MyProducer myProducer = (MyProducer) ca.getBean("myProducer");
        for(int i =0; i<10;i++){

            if(i%2==0){

                myProducer.send(("send i :"+i).getBytes());
            }else{
                myProducer.send(IOTest.fileToByteArray("D:/tmp.log"));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
